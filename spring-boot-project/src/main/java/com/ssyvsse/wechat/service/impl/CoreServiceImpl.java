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
					boolean isNum = content.matches("[0-9]+");
					if (!isNum) {

					} else {

					}
				}
				textTypeResp(respContent, requestMap, request, response);
			} else if (reqBaseMsg instanceof ReqImageMsg) {
				respContent = "暂不支持图片消息！";
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
	        	if("21".equals(eventKey)) {
	        		
	        	}
	        	textTypeResp(respContent,requestMap,request,response); 
	        }
	        else if (reqBaseMsg instanceof FollowEventMsg) {
	        	response.getWriter().write("success");
	        }

		} catch (Exception e) {
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
