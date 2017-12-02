package com.ssyvsse.wechat.msg.req;

/**
 * 文本消息
 *
 */
public class ReqTextMsg extends ReqBaseMsg {
	private String content;
	private String msgId;
	
	public ReqTextMsg(String toUserName, String fromUserName, Integer createTime, String msgType,String content,String msgId) {
		super(toUserName, fromUserName, createTime, msgType);
		this.content = content;
		this.msgId = msgId;
	}
	
	public ReqTextMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}