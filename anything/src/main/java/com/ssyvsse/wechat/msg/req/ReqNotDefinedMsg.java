package com.ssyvsse.wechat.msg.req;

/**
 * 没有找到对应的消息类型
 */
public class ReqNotDefinedMsg extends ReqBaseMsg {
	public ReqNotDefinedMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String msgId) {
		super(toUserName, fromUserName, createTime, msgType, msgId);
	}
}
