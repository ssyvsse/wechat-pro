package com.ssyvsse.base.controller.websocket;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.ssyvsse.base.controller.websocket.model.Chat;

/**
 * 收发消息处理
 * 
 * @author llb
 *
 * @Date 2018年1月8日 下午9:45:39 
 */
@Component
public class ChatWebSocketHandler implements WebSocketHandler {

	private Logger logger = LoggerFactory.getLogger(ChatWebSocketHandler.class);
	
	public static Map<Integer,Chat> chatMap;
	
	private static Map<String, Integer> ipMap;
	
	static{
		ipMap = new HashMap<String, Integer>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
		logger.info("已经建立连接");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		
	}

	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
		
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
