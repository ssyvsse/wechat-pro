package com.ssyvsse.wechat.msg.resp;

import com.ssyvsse.wechat.msg.req.ReqBaseMsg;

/**
 * 图片消息
 * 
 * @author llb
 *
 * @Date 2017年12月10日 下午5:12:15
 */
public class RespImageMsg extends RespBaseMsg {

	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public RespImageMsg() {
		this.msgType = "image";
	}

	public RespImageMsg(ReqBaseMsg reqBaseMsg) {
		super(reqBaseMsg);
		this.msgType = "image";
	}

	@Override
	protected void subXml(StringBuilder sb) {
		sb.append("<Image>\n");
		sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>");
		sb.append("</Image>\n");
	}

}
