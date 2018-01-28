package com.ssyvsse.wechat.msg.resp;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午10:34:16
 */
public class RespVoiceMsg extends RespBaseMsg {

	private String mediaId;

	public RespVoiceMsg() {
		this.msgType = "voice";
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	protected void subXml(StringBuilder sb) {
		if (mediaId == null || "".equals(mediaId)) {
			throw new NullPointerException("mediaId is null");
		}
		sb.append("< ![CDATA[voice] ]></MsgType><Voice><MediaId>< ![CDATA[").append(mediaId)
				.append("] ]></MediaId></Voice>");
	}

}
