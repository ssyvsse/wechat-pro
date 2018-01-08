package com.ssyvsse.base.controller.websocket.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

/**
 * @author llb
 *
 * @Date 2018年1月8日 下午9:46:46
 */
public class Chat {
	private Map<Integer, WebSocketSession> loginSessionMap;

	private Set<WebSocketSession> unloginSessionSet;

	public Chat() {
		loginSessionMap = new HashMap<Integer, WebSocketSession>();
		unloginSessionSet = new HashSet<WebSocketSession>();
	}

	public Map<Integer, WebSocketSession> getLoginSessionMap() {
		return loginSessionMap;
	}

	public void setLoginSessionMap(Map<Integer, WebSocketSession> loginSessionMap) {
		this.loginSessionMap = loginSessionMap;
	}

	public Set<WebSocketSession> getUnloginSessionSet() {
		return unloginSessionSet;
	}

	public void setUnloginSessionSet(Set<WebSocketSession> unloginSessionSet) {
		this.unloginSessionSet = unloginSessionSet;
	}
}
