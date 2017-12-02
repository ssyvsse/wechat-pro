package com.ssyvsse.wechat.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssyvsse.wechat.config.ApiConfigKit;
import com.ssyvsse.wechat.msg.MsgParser;
import com.ssyvsse.wechat.msg.req.ReqBaseMsg;
import com.ssyvsse.wechat.msg.req.ReqImageMsg;
import com.ssyvsse.wechat.msg.req.ReqLinkMsg;
import com.ssyvsse.wechat.msg.req.ReqNotDefinedMsg;
import com.ssyvsse.wechat.msg.req.ReqTextMsg;
import com.ssyvsse.wechat.msg.req.event.ClickEventMsg;
import com.ssyvsse.wechat.msg.req.event.FollowEventMsg;
import com.ssyvsse.wechat.msg.resp.RespBaseMsg;
import com.ssyvsse.wechat.msg.resp.RespTextMsg;
import com.ssyvsse.wechat.service.CoreService;
import com.ssyvsse.wechat.utils.MsgUtils;

/**
 * @author llb
 *
 * @Date 2017年11月30日 下午7:28:11
 */
@Service("coreService")
public class CoreServiceImpl implements CoreService {
	private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

	private ReqBaseMsg reqBaseMsg;

	private static int menu = 0;

	private static int subMenu = 0;

	private static boolean status = false;

	/**
	 * 处理微信发来的请求（包括事件的推送）
	 *
	 * @param request
	 * @return
	 */
	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MsgUtils.parseXml(request);
			System.out.println(requestMap);
			reqBaseMsg = getReqMsg(requestMap);
			if(requestMap.containsKey("Event")){
				if(requestMap.get("Event").equals("CLICK")){
					respContent = "请选择菜单:\n1、查看脑筋急转弯";
				}
				if(requestMap.get("Event").equals("VIEW")){
					respContent = "请选择菜单:\n2、查看计算题\n";
				}
				
			}
			// 发送过来的消息内容
			if (reqBaseMsg instanceof ReqTextMsg) {
				if (reqBaseMsg != null) {
					// 消息内容
					String content = ((ReqTextMsg) reqBaseMsg).getContent().trim();
					if (status) {
						boolean isNum = content.matches("[0-9]+");
						if (!isNum) {
							if (menu == 1) {
								if (subMenu == 1) {
									respContent = "三代人";
								} else if (subMenu == 2) {
									respContent = "牙齿";
								} else if (subMenu == 3) {
									respContent = "有一支是照明蜡烛";
								} else {

								}
							}
						} else {
							menu = Integer.parseInt(content);
							subMenu = Integer.parseInt(content);
							switch (menu) {
							case 1:
								respContent = showHead(subMenu);
								break;
							}
						}
					} else {
						status = true;
						respContent = "请选择菜单:\n1、查看脑筋急转弯\n2、查看计算题\n";
					}
				}
				textTypeResp(respContent, requestMap, request, response);
			} else if (reqBaseMsg instanceof ReqImageMsg) {
				respContent = "暂不支持图片消息！";
				String url = ((ReqImageMsg) reqBaseMsg).getPicUrl();
				System.out.println(url);
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
			} else if (reqBaseMsg instanceof ClickEventMsg) {

				String eventKey = requestMap.get("EventKey");

				if ("21".equals(eventKey)) {

				} else {
					respContent = "";
				}
				textTypeResp(respContent, requestMap, request, response);
			} else if (reqBaseMsg instanceof FollowEventMsg) {
				response.getWriter().write("success");
			}
		} catch (Exception e) {
			logger.error("解析微信发来的xml格式失败");
		}

	}

	private String showHead(int id) {
		switch (id) {
		case 1:
			return "两对父子去买帽子，为什么只买了三顶？";
		case 2:
			return "舔也硬，不舔也硬，想舒服睡，先搓搓它（人身上的东西）";
		case 3:
			return "研研十四岁生日的晚上，庆祝宴上点了十五支蜡烛。那是为什么？";
		default:
			return null;
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

		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/xml");
			response.getWriter().print(respMsgXml);
		} catch (IOException e) {
			logger.error("响应微信失败");
		}
	}

	// 接收文本消息，响应文本消息的处理
	protected void textTypeResp(String content, Map<String, String> requestMap, HttpServletRequest request,
			HttpServletResponse response) {
		RespTextMsg respTextMsg = new RespTextMsg(getReqMsg(requestMap));
		respTextMsg.setContent(content);
		sendMsg(respTextMsg, request, response);
	}

	// 接收到关注事件消息，响应文本消息的处理
	protected void textTypeResp(FollowEventMsg followEventMsg, HttpServletRequest request,
			HttpServletResponse response) {
		RespTextMsg respTextMsg = new RespTextMsg(followEventMsg);
		respTextMsg.setContent("你好,欢迎关注");
		sendMsg(respTextMsg, request, response);
	}
}
