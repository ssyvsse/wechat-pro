package com.ssyvsse.wechat.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssyvsse.wechat.config.ApiConfigKit;
import com.ssyvsse.wechat.config.WXUserApi;
import com.ssyvsse.wechat.dao.PicMsgMapper;
import com.ssyvsse.wechat.dao.WXUserInfoMapper;
import com.ssyvsse.wechat.entity.PicMsg;
import com.ssyvsse.wechat.entity.WXUserInfo;
import com.ssyvsse.wechat.msg.MsgParser;
import com.ssyvsse.wechat.msg.req.LocationMsg;
import com.ssyvsse.wechat.msg.req.ReqBaseMsg;
import com.ssyvsse.wechat.msg.req.ReqImageMsg;
import com.ssyvsse.wechat.msg.req.ReqLinkMsg;
import com.ssyvsse.wechat.msg.req.ReqNotDefinedMsg;
import com.ssyvsse.wechat.msg.req.ReqTextMsg;
import com.ssyvsse.wechat.msg.req.event.ClickEventMsg;
import com.ssyvsse.wechat.msg.req.event.FollowEventMsg;
import com.ssyvsse.wechat.msg.resp.RespBaseMsg;
import com.ssyvsse.wechat.msg.resp.RespImageMsg;
import com.ssyvsse.wechat.msg.resp.RespTextMsg;
import com.ssyvsse.wechat.service.CoreService;
import com.ssyvsse.wechat.utils.MsgUtils;

/**
 * 核心服务类
 * 
 * @author llb
 *
 * @Date 2017年12月9日
 */
@Service("coreService")
public class CoreServiceImpl implements CoreService {

	private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

	private ReqBaseMsg reqBaseMsg;

	@Autowired
	private PicMsgMapper picMsgMapper;

	@Autowired
	private WXUserInfoMapper wxUserInfoMapper;
	/**
	 * 是否接受图片消息 默认为 拒绝
	 */
	private static boolean ISUPLOADIMGPERMITTED = false;

	/**
	 * 处理微信发来的请求（包括事件的推送）
	 */
	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MsgUtils.parseXml(request);
			// 发送过来的消息内容
			// String content = requestMap.get("Content");

			reqBaseMsg = getReqMsg(requestMap);
			if (reqBaseMsg instanceof ReqTextMsg) {
				if (reqBaseMsg != null) {
					String content = ((ReqTextMsg) reqBaseMsg).getContent().trim();
					if ("你好".equals(content)) {
						respContent = "你好";
					} else if ("大家好".equals(content)) {
						respContent = "大家好";
					} else if ("同志们好".equals(content)) {
						respContent = "为人民服务";
					} else if (content.indexOf("图片") >= 0 && content.length() >= 3) {
						String str = content.substring(2);
						boolean result = true;
						for (int i = 0; i < str.length(); i++) {
							if (!Character.isDigit(str.charAt(i))) {
								result = false;
							}
						}
						if (result) {
							String mediaId = picMsgMapper.findMediaIdById(Integer.valueOf(str),
									reqBaseMsg.getFromUserName());
							if (mediaId != null) {
								imageTypeResp(mediaId, requestMap, request, response);
							} else {
								textTypeResp("找不到该图片", requestMap, request, response);
							}
						} else {
							respContent = "输入格式错误，请输入图片+数字";
							textTypeResp(respContent, requestMap, request, response);
						}
					} else {
						respContent = content;
						textTypeResp(respContent, requestMap, request, response);
					}
				}
			} else if (reqBaseMsg instanceof ReqImageMsg) {
				if (!ISUPLOADIMGPERMITTED) {
					respContent = "请点击菜单上传图片";
					textTypeResp(respContent, requestMap, request, response);
					return;
				}
				// respContent = "暂不支持图片消息！";
				PicMsg picMsg = new PicMsg();
				picMsg.setMediaId(((ReqImageMsg) reqBaseMsg).getMediaId());
				picMsg.setPicUrl(((ReqImageMsg) reqBaseMsg).getPicUrl());
				picMsg.setCreateBy(reqBaseMsg.getFromUserName());
				picMsg.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date(reqBaseMsg.getCreateTime() * 1000L)));
				picMsgMapper.addPicMsg(picMsg);
				respContent = "上传图片成功,图片id为:" + picMsgMapper.findIdByMediaId(picMsg.getMediaId()) + "\n查询图片请输入(图片+id)";
				ISUPLOADIMGPERMITTED = false;
				textTypeResp(respContent, requestMap, request, response);
			}

			else if (reqBaseMsg instanceof ReqLinkMsg) {
				respContent = "暂不支持链接消息！";
				textTypeResp(respContent, requestMap, request, response);
			}

			else if (reqBaseMsg instanceof ReqNotDefinedMsg) {
				logger.error("未能识别的消息类型。 消息 xml 内容为：\n");
				respContent = "无法识别的消息";
				textTypeResp(respContent, requestMap, request, response);
			}

			else if (reqBaseMsg instanceof ClickEventMsg) {

				String eventKey = requestMap.get("EventKey");
				if ("21".equals(eventKey)) {
					respContent = "你要吃大闸蟹吗?";
					textTypeResp(respContent, requestMap, request, response);
				} else if ("11".equals(eventKey)) {
					respContent = "请发送要上传的图片";
					ISUPLOADIMGPERMITTED = true;
					textTypeResp(respContent, requestMap, request, response);
				}
			} else if (reqBaseMsg instanceof FollowEventMsg) {
				FollowEventMsg followEventMsg = (FollowEventMsg) reqBaseMsg;
				WXUserInfo oldUserInfo = wxUserInfoMapper.findByOpenid(followEventMsg.getFromUserName());
				if ("subscribe".equals(followEventMsg.getEvent())) {
					/* 关注 */
					WXUserInfo userInfo = WXUserApi.getUserInfo(followEventMsg.getFromUserName());
					if (oldUserInfo == null) {
						logger.info("新用户");
						wxUserInfoMapper.save(userInfo);
					} else {
						logger.info("更新用户信息");
						userInfo.setId(oldUserInfo.getId());
						wxUserInfoMapper.save(userInfo);
					}
					textTypeResp(followEventMsg, request, response);
				} else if ("unsubscribe".equals(((FollowEventMsg) reqBaseMsg).getEvent())) {
					/* 取消关注 */
					oldUserInfo.setSubscribe(0);
					wxUserInfoMapper.save(oldUserInfo);
				}
			}
			else if(reqBaseMsg instanceof LocationMsg){
				LocationMsg locationMsg = (LocationMsg)reqBaseMsg;
				String location = getLocationMsg(locationMsg.getLatitude(),locationMsg.getLongitude());
				System.out.println(location);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解析微信发来的xml格式失败");
		}
	}

	private ReqBaseMsg getReqMsg(Map<String, String> requestMap) {
		reqBaseMsg = MsgParser.parse(requestMap);
		return reqBaseMsg;
	}

	public void sendMsg(RespBaseMsg respBaseMsg, HttpServletRequest request, HttpServletResponse response) {
		String respMsgXml = respBaseMsg.toXml();
		// 开发模式向控制台输出即将发送的消息的 xml 内容
		if (ApiConfigKit.isDevMode()) {
			logger.info("发送消息:" + respMsgXml);
		}
		if (respBaseMsg instanceof RespTextMsg) {
			try {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/xml");
				response.getWriter().print(respMsgXml);
			} catch (IOException e) {
				logger.error("响应微信失败 ==>>> textRespErr");
			}

		} else if (respBaseMsg instanceof RespImageMsg) {
			try {
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/x-jpg");
				response.getWriter().print(respMsgXml);
			} catch (IOException e) {
				logger.error("响应微信失败 ==>>> imgRespErr");
			}
		}
	}

	/**
	 * 接收文本消息，响应文本消息的处理
	 * 
	 * @param content
	 * @param requestMap
	 * @param request
	 * @param response
	 */
	protected void textTypeResp(String content, Map<String, String> requestMap, HttpServletRequest request,
			HttpServletResponse response) {
		RespTextMsg respTextMsg = new RespTextMsg(getReqMsg(requestMap));
		respTextMsg.setContent(content);
		sendMsg(respTextMsg, request, response);
	}

	protected void imageTypeResp(String mediaId, Map<String, String> requestMap, HttpServletRequest request,
			HttpServletResponse response) {
		RespImageMsg respImageMsg = new RespImageMsg(getReqMsg(requestMap));
		respImageMsg.setMediaId(mediaId);
		sendMsg(respImageMsg, request, response);
	}

	/**
	 * 接收到关注事件消息，响应文本消息的处理
	 * 
	 * @param followEventMsg
	 * @param request
	 * @param response
	 */
	protected void textTypeResp(FollowEventMsg followEventMsg, HttpServletRequest request,
			HttpServletResponse response) {
		RespTextMsg respTextMsg = new RespTextMsg(followEventMsg);
		respTextMsg.setContent("你好,欢迎关注");
		sendMsg(respTextMsg, request, response);
	}
	
	private static String getLocationMsg(double latitude,double longitude){
		String message = "";
		        String url = String.format(
		            "http://maps.google.cn/maps/api/geocode/json?latlng=%s,%s&language=CN",
		            latitude,longitude);
		        URL myURL = null;
		        URLConnection httpsConn = null;
		        try {
		            myURL = new URL(url);
		        } catch (MalformedURLException e) {
		          e.printStackTrace();
		        }
		        try {
		            httpsConn = (URLConnection) myURL.openConnection();
		            if (httpsConn != null) {
		                InputStreamReader insr = new InputStreamReader(
		                        httpsConn.getInputStream(), "UTF-8");
		                BufferedReader br = new BufferedReader(insr);
		                String data = null;
		                while ((data = br.readLine()) != null) {
		                message = message+data;
		                }
		                insr.close();
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		return message;
		}
}
