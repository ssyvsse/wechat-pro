package com.ssyvsse.wechat.msg.req;

/**
 * 文本消息
 *
 */
public class ReqTextMsg extends ReqBaseMsg {
	/**
	 * 文本消息内容
	 */
	private String content;

	public ReqTextMsg(String toUserName, String fromUserName, Integer createTime, String content, String msgId) {
		super(toUserName, fromUserName, createTime, "text", msgId);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}