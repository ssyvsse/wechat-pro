package com.ssyvsse.wechat.msg.req;

/**
 * 图片消息
 *
 */
public class ReqImageMsg extends ReqBaseMsg {
	/**
	 * 图片链接（由系统生成）
	 */
	private String picUrl;
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String mediaId;

	public ReqImageMsg(String toUserName, String fromUserName, Integer createTime, String picUrl, String mediaId,
			String msgId) {
		super(toUserName, fromUserName, createTime, "image", msgId);
		this.picUrl = picUrl;
		this.mediaId = mediaId;
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

}