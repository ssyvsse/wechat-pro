package com.ssyvsse.wechat.msg;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssyvsse.wechat.config.ApiConfigKit;
import com.ssyvsse.wechat.msg.req.LocationMsg;
import com.ssyvsse.wechat.msg.req.ReqBaseMsg;
import com.ssyvsse.wechat.msg.req.ReqImageMsg;
import com.ssyvsse.wechat.msg.req.ReqLinkMsg;
import com.ssyvsse.wechat.msg.req.ReqNotDefinedMsg;
import com.ssyvsse.wechat.msg.req.ReqTextMsg;
import com.ssyvsse.wechat.msg.req.ReqVoiceMsg;
import com.ssyvsse.wechat.msg.req.event.ClickEventMsg;
import com.ssyvsse.wechat.msg.req.event.FollowEventMsg;

public class MsgParser {

	private static Logger logger = LoggerFactory.getLogger(MsgParser.class);

	public static ReqBaseMsg parse(Map<String, String> requestMap) {
		if (ApiConfigKit.isDevMode()) {
			logger.info("" + requestMap);
		}
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
			logger.info("文字消息");
			return new ReqTextMsg(toUserName, fromUserName, createTime, content, msgId);
		}
		if ("image".equals(msgType)) {
			logger.info("图片消息");
			return new ReqImageMsg(toUserName, fromUserName, createTime, requestMap.get("PicUrl"),
					requestMap.get("MediaId"), msgId);
		}
		if ("voice".equals(msgType)) {
			logger.info("音频消息");
			return new ReqVoiceMsg(toUserName, fromUserName, createTime, msgId, requestMap.get("MediaId"),
					requestMap.get("Format"), requestMap.get("Recognition"));
		}
		if ("link".equals(msgType)) {
			logger.info("链接");
			return new ReqLinkMsg(toUserName, fromUserName, createTime, msgId, requestMap.get("Title"),
					requestMap.get("Description"), requestMap.get("Url"));
		}
		if ("event".equals(msgType)) {
			if ("subscribe".equals(event)) {
				logger.info("关注");
				return new FollowEventMsg(toUserName, fromUserName, createTime, msgType,
						FollowEventMsg.EVENT_INFOLLOW_SUBSCRIBE, msgId);
			}
			if ("unsubscribe".equals(event)) {
				logger.info("取消");
				return new FollowEventMsg(toUserName, fromUserName, createTime, msgType,
						FollowEventMsg.EVENT_INFOLLOW_UNSUBSCRIBE, msgId);
			}
			if ("CLICK".equals(event)) {
				logger.info("点击按钮");
				return new ClickEventMsg(toUserName, fromUserName, createTime, msgType, event, eventKey, msgId);
			}
			if ("LOCATION".equals(event)) {
				logger.info("包含地理位置的信息");
				return new LocationMsg(toUserName, fromUserName, createTime, msgId,
						Double.valueOf(requestMap.get("Latitude")), Double.valueOf(requestMap.get("Longitude")),
						Double.valueOf(requestMap.get("Precision")));
			}
		}

		return new ReqNotDefinedMsg(toUserName, fromUserName, createTime, msgType, msgId);

	}
}
