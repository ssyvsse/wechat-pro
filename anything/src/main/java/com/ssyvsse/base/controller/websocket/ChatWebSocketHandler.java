package com.ssyvsse.base.controller.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.ssyvsse.base.controller.websocket.model.ChatMessage;

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
	
	public final static Map<String,WebSocketSession> sessionMap = new HashMap<String,WebSocketSession>();
	
	public final static Map<String,List<ChatMessage>> chatMsgListMap = new HashMap<String,List<ChatMessage>>();
	
	public final static Map<String,List<ChatMessage>> chatMsgListMapNew = new HashMap<String,List<ChatMessage>>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("已经建立连接");
		Object object = session.getAttributes().get("uid");
		String uid = null;
		if(object != null && object instanceof String){
			uid = (String)object;
			sessionMap.put(uid, session);
			List<ChatMessage> chatMsgList = chatMsgListMap.get(uid);
			chatMsgListMap.put(uid, chatMsgList);
			Gson gson = new Gson();
			session.sendMessage(new TextMessage(gson.toJson(chatMsgListMap)));
		}
		
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.info("连接关闭");
		Object object = session.getAttributes().get("uid");
		String uid = null;
		if(object != null && object instanceof String){
			uid = (String)object;
			sessionMap.remove(uid);
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		logger.info(message.getPayload().toString());
		Object object = message.getPayload();
		Gson gson = new Gson();
		String uid = (String)session.getAttributes().get("uid");
		if(object.toString().equals("000")){
			session.sendMessage(new TextMessage("000"));
		}else if("users".equals(object.toString())){
			Set<String> userSet = ChatWebSocketHandler.sessionMap.keySet();
			List<String> userList = new ArrayList<String>();
			for (String string : userSet) {
				userList.add(string);
			}
			session.sendMessage(new TextMessage(userList.toString()));
		}else{
			ChatMessage chatMessage = null;
			try {
				chatMessage = gson.fromJson(object.toString(), ChatMessage.class);
				System.out.println(chatMessage);
				List<ChatMessage> chatMsgList = chatMsgListMap.get(uid);
				List<ChatMessage> chatMsgListNew = new ArrayList<ChatMessage>();
				if(chatMsgList==null){
					chatMsgList = new ArrayList<ChatMessage>();
					chatMsgList.add(chatMessage);
					chatMsgListMap.put(uid, chatMsgList);
					session.sendMessage(new TextMessage(gson.toJson(chatMsgListMap)));
				}else{
					String toName = chatMessage.getToName();
					if(toName.equals(uid)){
						chatMsgList.add(chatMessage);
						chatMsgListNew.add(chatMessage);
						chatMsgListMap.put(uid, chatMsgList);
						chatMsgListMapNew.put(uid, chatMsgListNew);
					}else{
						chatMsgList.add(chatMessage);
						chatMsgListNew.add(chatMessage);
						chatMsgListMap.put(uid, chatMsgList);
						chatMsgListMapNew.put(uid, chatMsgListNew);
						chatMsgListMapNew.put(toName, chatMsgListNew);
					}
					session.sendMessage(new TextMessage(gson.toJson(chatMsgListMapNew)));
				}
				
			} catch (Exception e) {
				session.sendMessage(new TextMessage("类型转换错误"));
			}
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
