package com.ssyvsse.wechat.msg.resp;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午10:39:10
 */
public class RespVideoMsg extends RespBaseMsg {

	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id
	 */
	private String mediaId;

	/**
	 * 视频消息的标题
	 */
	private String title;

	/**
	 * 视频消息的描述
	 */
	private String description;

	public RespVideoMsg() {
		this.msgType = "video";
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected void subXml(StringBuilder sb) {
		if (mediaId == null || "".equals(mediaId)) {
			throw new NullPointerException("mediaId is null.");
		}
		sb.append("<Video><MediaId>< ![CDATA[").append(mediaId).append("] ]></MediaId>");
		if (title != null && !"".equals(title)) {
			sb.append("<Title>< ![CDATA[").append(title).append("] ]></Title>");
		}
		if (description != null && !"".equals(description)) {
			sb.append("<Description>< ![CDATA[").append(description).append("] ]></Description>");
		}
		sb.append("</Video>");
	}

}
