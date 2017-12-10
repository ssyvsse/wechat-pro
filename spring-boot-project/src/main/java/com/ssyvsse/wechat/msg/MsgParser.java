package com.ssyvsse.wechat.msg;

import java.util.Map;

import com.ssyvsse.wechat.msg.req.LocationMsg;
import com.ssyvsse.wechat.msg.req.ReqBaseMsg;
import com.ssyvsse.wechat.msg.req.ReqImageMsg;
import com.ssyvsse.wechat.msg.req.ReqLinkMsg;
import com.ssyvsse.wechat.msg.req.ReqNotDefinedMsg;
import com.ssyvsse.wechat.msg.req.ReqTextMsg;
import com.ssyvsse.wechat.msg.req.event.ClickEventMsg;
import com.ssyvsse.wechat.msg.req.event.FollowEventMsg;

public class MsgParser {

	private MsgParser() {
	}

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
			System.out.println("文字消息");
			return new ReqTextMsg(toUserName, fromUserName, createTime, msgType, content, msgId);
		}
		if ("image".equals(msgType)) {
			System.out.println("图片消息");
			return new ReqImageMsg(toUserName, fromUserName, createTime, msgType, requestMap.get("PicUrl"),
					requestMap.get("MediaId"), msgId);
		}
		if ("link".equals(msgType)) {
			System.out.println("链接");
			return new ReqLinkMsg(toUserName, fromUserName, createTime, msgType);
		}
		if ("event".equals(msgType)) {
			if ("subscribe".equals(event)) {
				System.out.println("关注");
				return new FollowEventMsg(toUserName, fromUserName, createTime, msgType,
						FollowEventMsg.EVENT_INFOLLOW_SUBSCRIBE);
			}
			if ("unsubscribe".equals(event)) {
				System.out.println("取消");
				return new FollowEventMsg(toUserName, fromUserName, createTime, msgType,
						FollowEventMsg.EVENT_INFOLLOW_UNSUBSCRIBE);
			}
			if ("CLICK".equals(event)) {
				System.out.println("点击按钮");
				return new ClickEventMsg(toUserName, fromUserName, createTime, msgType, event, eventKey);
			}
			if ("LOCATION".equals(event)) {
				System.out.println("包含地理位置的信息");
				return new LocationMsg(toUserName, fromUserName, createTime, msgType,Double.valueOf(requestMap.get("Latitude")),
						Double.valueOf(requestMap.get("Longitude")), Double.valueOf(requestMap.get("Precision")));
			}
		}

		return new ReqNotDefinedMsg(toUserName, fromUserName, createTime, msgType);

	}
}
