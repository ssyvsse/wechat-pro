package com.ssyvsse.wechat.msg.req.event;

import com.ssyvsse.wechat.msg.req.EventInMsg;

public class ClickEventMsg extends EventInMsg {
	private String eventKey;

	public ClickEventMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String event,
			String eventKey, String msgId) {
		super(toUserName, fromUserName, createTime, msgType, event, msgId);
		this.eventKey = eventKey;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}