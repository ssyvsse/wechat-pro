package com.ssyvsse.wechat.msg;

import java.util.Map;

import com.ssyvsse.wechat.msg.req.ReqBaseMsg;
import com.ssyvsse.wechat.msg.req.ReqImageMsg;
import com.ssyvsse.wechat.msg.req.ReqLinkMsg;
import com.ssyvsse.wechat.msg.req.ReqNotDefinedMsg;
import com.ssyvsse.wechat.msg.req.ReqTextMsg;
import com.ssyvsse.wechat.msg.req.event.ClickEventMsg;
import com.ssyvsse.wechat.msg.req.event.FollowEventMsg;

public class MsgParser {
	
	private MsgParser() {}
	
	public static ReqBaseMsg parse(Map<String, String> requestMap) {
   
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		int createTime = Integer.parseInt(requestMap.get("CreateTime"));
		// 消息类型
		String msgType = requestMap.get("MsgType");
		String content = requestMap.get("Content");
		String msgId = requestMap.get("MsgId");
		String event = requestMap.get("Event");
		String eventKey = requestMap.get("EventKey"); 
		if ("text".equals(msgType)) {
			return new ReqTextMsg(toUserName, fromUserName, createTime, msgType,content,msgId);
		}
		if ("image".equals(msgType)) {
			return new ReqImageMsg(toUserName,  fromUserName, createTime, msgType);
		}
		if ("link".equals(msgType)) {
			return new ReqLinkMsg(toUserName, fromUserName, createTime, msgType);
		}
		if ("event".equals(msgType)) {
			if("subscribe".equals(event)) {
				return new FollowEventMsg(toUserName, fromUserName, createTime, msgType, event);
			}
			if("CLICK".equals(event)) {
				return new ClickEventMsg(toUserName, fromUserName, createTime, msgType, event ,eventKey);
			}
		}
			
		return new ReqNotDefinedMsg(toUserName, fromUserName, createTime, msgType);
				
	}
}
