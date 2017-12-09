package com.ssyvsse.wechat.msg.resp;

import com.ssyvsse.wechat.msg.req.ReqBaseMsg;

/**
 * 文本消息
 *
 */
public class RespTextMsg extends AbstractRespBaseMsg {
    // 回复的消息内容
    private String content;
    
    public RespTextMsg() {
		this.msgType = "text";
	}
	
	public RespTextMsg(ReqBaseMsg reqBaseMsg) {
		super(reqBaseMsg);
		this.msgType = "text";
	}
	
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
    	this.content = content;
    }

	@Override
	protected void subXml(StringBuilder sb) {
		if (null == content) {
			throw new NullPointerException("content is null");
		}
		sb.append("<Content><![CDATA[").append(content).append("]]></Content>\n");
	}
}
