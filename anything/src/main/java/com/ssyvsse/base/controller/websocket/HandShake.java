package com.ssyvsse.base.controller.websocket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.ssyvsse.base.entity.User;

/**
 * Socket建立连接（握手）和断开
 * 
 * @author llb
 *
 * @Date 2018年1月8日 下午9:49:16 
 */
public class HandShake implements HandshakeInterceptor {

	private Logger logger = LoggerFactory.getLogger(HandShake.class);
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("WebSocket:user[ID:"+((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("user") + "]已经建立连接");
//		logger.info("sessionId:"+((ServletServerHttpRequest) request).getServletRequest().getSession(false).getId());
//		logger.info("getCreationTime:"+((ServletServerHttpRequest) request).getServletRequest().getSession(false).getCreationTime());
//		logger.info("getLastAccessedTime:"+df.format(new Date(((ServletServerHttpRequest) request).getServletRequest().getSession(false).getLastAccessedTime())));
//		logger.info("getMaxInactiveInterval:"+((ServletServerHttpRequest) request).getServletRequest().getSession(false).getMaxInactiveInterval());
//		logger.info("getMaxInactiveInterval:"+((ServletServerHttpRequest) request).getServletRequest().getServletContext());
//		Enumeration<String> enumer = ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttributeNames();
//		while (enumer.hasMoreElements()) {
//			String string = (String) enumer.nextElement();
//			System.out.println(string);
//		}
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			User uid = (User) session.getAttribute("user");
			if(uid!=null){
				logger.info("uid:"+uid.getId());
				attributes.put("uid", uid.getId());
			}else{
				return false;
			}
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		logger.info("afterHandshake:");
	}

}
