package com.ssyvsse.wechat.msg.req;

public abstract class EventInMsg extends ReqBaseMsg {
	protected String event;

	public EventInMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String event,
			String msgId) {
		super(toUserName, fromUserName, createTime, msgType, msgId);
		this.event = event;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
