package com.ssyvsse.wechat.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssyvsse.wechat.config.ApiConfigKit;
import com.ssyvsse.wechat.msg.MsgParser;
import com.ssyvsse.wechat.msg.req.LocationMsg;
import com.ssyvsse.wechat.msg.req.ReqBaseMsg;
import com.ssyvsse.wechat.msg.req.ReqImageMsg;
import com.ssyvsse.wechat.msg.req.ReqLinkMsg;
import com.ssyvsse.wechat.msg.req.ReqNotDefinedMsg;
import com.ssyvsse.wechat.msg.req.ReqTextMsg;
import com.ssyvsse.wechat.msg.req.ReqVoiceMsg;
import com.ssyvsse.wechat.msg.req.event.ClickEventMsg;
import com.ssyvsse.wechat.msg.req.event.FollowEventMsg;
import com.ssyvsse.wechat.msg.resp.RespBaseMsg;
import com.ssyvsse.wechat.msg.resp.RespImageMsg;
import com.ssyvsse.wechat.msg.resp.RespTextMsg;
import com.ssyvsse.wechat.pojo.WXUserInfo;
import com.ssyvsse.wechat.service.CoreService;
import com.ssyvsse.wechat.service.WXUserInfoService;
import com.ssyvsse.wechat.thread.TokenThread;
import com.ssyvsse.wechat.utils.MsgUtils;
import com.ssyvsse.wechat.utils.WXUtil;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午8:48:28
 */
@Service
public class CoreServiceImpl implements CoreService {

	private static Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

	@Autowired
	private WXUserInfoService wxUserInfoService;
	
	private ReqBaseMsg reqBaseMsg;

	/**
	 * 处理微信发来的请求（包括事件的推送）
	 */
	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		String respContent = "请求处理异常，请稍候尝试！";
		try {
			// xml请求解析
			Map<String, String> requestMap = MsgUtils.parseXml(request);
			// 发送过来的消息内容
			// String content = requestMap.get("Content");
			reqBaseMsg = getReqMsg(requestMap);
			if (reqBaseMsg instanceof ReqTextMsg) {
				// 文本消息
			} else if (reqBaseMsg instanceof ReqImageMsg) {
				// 图片消息
			} else if (reqBaseMsg instanceof ReqLinkMsg) {
				// 链接消息
			} else if (reqBaseMsg instanceof ReqVoiceMsg) {
				// 声音消息
			} else if (reqBaseMsg instanceof ClickEventMsg) {
				// 点击消息
			} else if (reqBaseMsg instanceof FollowEventMsg) {
				// 关注消息
				FollowEventMsg followEventMsg = (FollowEventMsg) reqBaseMsg;
				if ("subscribe".equals(followEventMsg.getEvent())) {
					WXUserInfo wxUserInfo = WXUtil.getWXUserInfo(TokenThread.accessToken.getAccess_token(),followEventMsg.getFromUserName());
					if(wxUserInfo==null) {
						logger.error("获取不到");
					}else {
						logger.info("获取微信信息成功");
						wxUserInfo.setCreateTime(new Date());
						wxUserInfo.setCreateBy("wechat subscribe");
						wxUserInfoService.saveAndFlush(wxUserInfo);
					}
					/* 关注 */
					textTypeResp(followEventMsg, request, response);
				} else if ("unsubscribe".equals(((FollowEventMsg) reqBaseMsg).getEvent())) {
					/* 取消关注 */
				}
				textTypeResp(followEventMsg, request, response);
			} else if (reqBaseMsg instanceof LocationMsg) {
				// 位置消息
				LocationMsg locationMsg = (LocationMsg) reqBaseMsg;
				String location = getLocationMsg(locationMsg.getLatitude(), locationMsg.getLongtitude());
				// logger.info("用户所在地理位置为:" + location);
			} else {
				ReqNotDefinedMsg noDefinedMsg = (ReqNotDefinedMsg) reqBaseMsg;

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解析微信发来的xml格式失败:" + e.getMessage());
		}

	}

	private ReqBaseMsg getReqMsg(Map<String, String> requestMap) {
		reqBaseMsg = MsgParser.parse(requestMap);
		return reqBaseMsg;
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
	 * 响应图片消息
	 * 
	 * @param mediaId
	 * @param requestMap
	 * @param request
	 * @param response
	 */
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

	private static String getLocationMsg(double latitude, double longitude) {
		String message = "";
		String url = String.format("http://maps.google.cn/maps/api/geocode/json?latlng=%s,%s&language=CN", latitude,
				longitude);
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
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				while ((data = br.readLine()) != null) {
					message = message + data;
				}
				insr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
}
