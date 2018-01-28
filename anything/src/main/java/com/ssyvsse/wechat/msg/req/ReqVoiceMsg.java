package com.ssyvsse.wechat.msg.req;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午9:20:00
 */
public class ReqVoiceMsg extends ReqBaseMsg {

	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String mediaId;

	/**
	 * 语音格式，如amr，speex等
	 */
	private String format;

	/**
	 * 语音识别结果
	 */
	private String recognition;

	public ReqVoiceMsg(String toUserName, String fromUserName, Integer createTime, String msgId, String mediaId,
			String format, String recognition) {
		super(toUserName, fromUserName, createTime, "voice", msgId);
		this.mediaId = mediaId;
		this.format = format;
		this.recognition = recognition;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

}
