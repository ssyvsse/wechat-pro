package com.ssyvsse.wechat.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssyvsse.utils.DateUtils;
import com.ssyvsse.wechat.entity.WXUserInfo;
import com.ssyvsse.wechat.thread.TokenThread;
import com.ssyvsse.wechat.utils.WeixinUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 微信用户信息相关接口
 * 
 * @author llb
 *
 * @Date 2017年12月10日 下午8:16:18
 */
public class WXUserApi {
	
	private static String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 获取用户基本信息（包括UnionID机制）
	 * @param openId 普通用户的标识，对当前公众号唯一
	 * @return ApiResult
	 */
	public static WXUserInfo getUserInfo(String openId) {
		WXUserInfo userInfo = null;
		String access_token = TokenThread.accessToken.getAccess_token();
		String requestUrl = getUserInfo.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				userInfo = new WXUserInfo();
				userInfo.setSubscribe(Integer.parseInt(jsonObject.getString("subscribe")));
				userInfo.setOpenid(jsonObject.getString("openid"));
				userInfo.setNickname(jsonObject.getString("nickname")); 
				userInfo.setSex(jsonObject.getInt("sex"));
				userInfo.setLanguage(jsonObject.getString("language"));
				userInfo.setCity(jsonObject.getString("city"));
				userInfo.setProvince(jsonObject.getString("province"));
				userInfo.setCountry(jsonObject.getString("country"));
				userInfo.setHeadimgurl(jsonObject.getString("headimgurl")); 
				userInfo.setSubscribe_time(DateUtils.stampToDate(jsonObject.getString("subscribe_time"))); 
				userInfo.setUnionid(jsonObject.getString("unionid")); 
				userInfo.setRemark(jsonObject.getString("remark"));
				userInfo.setGroupid(Integer.parseInt(jsonObject.getString("groupid")));
			} catch (JSONException e) {
				userInfo = null;
	        }
		}
		return userInfo;
	}
	
	private static String getFollowers = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	/**
	 * 获取用户列表
	 * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
	 * @return ApiResult
	 */
	public static JSONObject getFollowers(String nextOpenid) {
		String requestUrl = null;
		if (nextOpenid != null) {
			requestUrl = getFollowers.replace("ACCESS_TOKEN",  TokenThread.accessToken.getAccess_token()).replace("NEXT_OPENID", nextOpenid);
		}else {
			requestUrl = getFollowers.replace("ACCESS_TOKEN",  TokenThread.accessToken.getAccess_token()).replace("&next_openid=NEXT_OPENID", "");
		}
		return WeixinUtil.httpRequest(requestUrl, "POST",  null);
	}
	
	/**
	 * 批量获取用户基本信息
	 * @param jsonStr json字符串
	 * @return ApiResult
	 */
	public static JSONObject batchGetUserInfo(String jsonStr) {
		String requestUrl = batchGetUserInfo.replace("ACCESS_TOKEN",  TokenThread.accessToken.getAccess_token());
		return WeixinUtil.httpRequest(requestUrl, "POST",  jsonStr);
	}
	
	private static String batchGetUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	/**
	 * 批量获取用户基本信息
	 * @param openIdList openid列表
	 * @return ApiResult
	 */
	public static JSONObject batchGetUserInfo(List<String> openIdList) {
		Map<String, List<Map<String, Object>>> userListMap = new HashMap<String, List<Map<String, Object>>>();
		
		List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
		for (String openId : openIdList) {
			Map<String, Object> mapData = new HashMap<String, Object>();
			mapData.put("openid", openId);
			mapData.put("lang", "zh_CN");
			userList.add(mapData);
		}
		userListMap.put("user_list", userList);
		return  batchGetUserInfo(JSONObject.fromObject(userListMap).toString());
	}
	
	private static String updateRemarkUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=";
	
	/**
	 * 设置备注名
	 * @param openid 用户标识
	 * @param remark 新的备注名，长度必须小于30字符
	 * @return {ApiResult}
	 */
	public static JSONObject updateRemark(String openid, String remark) {
		String requestUrl = updateRemarkUrl.replace("ACCESS_TOKEN",  TokenThread.accessToken.getAccess_token()).replace("OPENID", openid);
		return WeixinUtil.httpRequest(requestUrl, "GET", null);
	}
}
