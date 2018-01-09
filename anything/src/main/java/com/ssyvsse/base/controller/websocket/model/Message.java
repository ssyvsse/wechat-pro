package com.ssyvsse.base.controller.websocket.model;

import java.util.Date;

/**
 * 消息类
 * 
 * @author llb
 *
 * @Date 2018年1月9日 下午7:47:27
 */
public class Message {

	// 发送者
	public Integer from;
	// 发送者名称
	public String fromName;
	// 接收者
	public Integer to;
	// 接收者名称
	public String toName;
	// 发送的文本
	public String text;
	// 发送日期
	public Date date;

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

}
