package com.ssyvsse.wechat.msg.req;
/**
 * 图片消息
 *
 */
public class ReqImageMsg extends ReqBaseMsg {
	private String picUrl;
	private String mediaId;
	private String msgId;
	
	public ReqImageMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getPicUrl() {
		return picUrl;
	}
	
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}