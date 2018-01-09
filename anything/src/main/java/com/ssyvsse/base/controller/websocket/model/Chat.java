package com.ssyvsse.base.controller.websocket.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

/**
 * @author llb
 *
 * @Date 2018年1月8日 下午9:46:46
 */
public class Chat {

	private Map<String, WebSocketSession> loginSessionMap;

	public Chat() {
		loginSessionMap = new HashMap<String, WebSocketSession>();
	}

	public Map<String, WebSocketSession> getLoginSessionMap() {
		return loginSessionMap;
	}

	public void setLoginSessionMap(Map<String, WebSocketSession> loginSessionMap) {
		this.loginSessionMap = loginSessionMap;
	}

}
