package com.ssyvsse.base.controller.websocket.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天信息类
 * 
 * @author llb
 *
 * @Date 2018年1月9日 下午7:47:38
 */
public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	// 发送者
	private String fromUid;
	// 发送者名称
	private String fromName;
	// 接收者
	private String toName;
	// 发送的文本
	private String text;
	// 发送日期
	private Date date;

	public String getFromUid() {
		return fromUid;
	}

	public void setFromUid(String fromUid) {
		this.fromUid = fromUid;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ChatMessage [fromUid=" + fromUid + ", fromName=" + fromName + ", toName=" + toName + ", text=" + text
				+ ", date=" + date + "]";
	}

}
