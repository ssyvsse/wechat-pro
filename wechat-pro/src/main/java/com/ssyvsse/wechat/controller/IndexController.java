package com.ssyvsse.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.crawl.entity.Novel;
import com.ssyvsse.dao.NovelMapper;
import com.ssyvsse.wechat.utils.WechatPropUtil;
import com.ssyvsse.wechat.utils.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * @author llb
 *
 * @Date 2017年11月28日 下午9:32:14
 */
@Controller
public class IndexController {

	/**
	 * 通过code换取网页授权access_token
	 */
	private String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	/**
	 * 刷新access_token（如果需要）
	 */
	private String refreshUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 */
	private String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 检验授权凭证（access_token）是否有效
	 */
	private String validateUrl = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	@Autowired
	private IUserService userService;

	@Autowired
	private NovelMapper novelMapper;

	@RequestMapping({ "/welcome", "/" })
	public String welcome(HttpServletRequest request) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int size = 1;
		request.setAttribute("prevPage", pageNo > 1 ? pageNo - 1 : 1);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("nextPage", pageNo + 1 >= 501 ? 501 : pageNo + 1);
		request.setAttribute("lastPage", 501);
		request.setAttribute("list", novelMapper.findAll((pageNo - 1) * size, size));
		// System.out.println(request.getRequestURL().toString());
		// try {
		// String code = request.getParameter("code");
		// System.out.println(code);
		// JSONObject j = getAccessToken(code);
		// if (!j.has("errcode")) {
		// String accessToken = (String) j.get("access_token");
		// System.out.println(accessToken);
		// String refreshToken = (String) j.get("refresh_token");
		// System.out.println(refreshToken);
		// String openid = (String) j.get("openid");
		// System.out.println(openid);
		// JSONObject va = isTokenValid(accessToken, openid);
		// if ((Integer) va.get("errcode") == 0) {
		//
		// JSONObject userInfo = getUserInfo(accessToken, openid);
		// if (!userInfo.has("errcode")) {
		// System.out.println(userInfo);
		// String openid2 = userInfo.getString("openid");
		// String nickname = userInfo.getString("nickname");
		// String sex = userInfo.getString("sex");
		// String province = userInfo.getString("province");
		// String city = userInfo.getString("city");
		// String headimgurl = userInfo.getString("headimgurl");
		// String privilege = userInfo.getString("privilege");
		// String unionid = userInfo.getString("unionid");
		// WXUserInfo wxUser = new WXUserInfo();
		// wxUser.setOpenId(openid2);
		// wxUser.setNickname(nickname);
		// wxUser.setSex(Integer.parseInt(sex));
		// wxUser.setProvince(province);
		// wxUser.setCity(city);
		// wxUser.setHeadImgUrl(headimgurl);
		// wxUser.setPrivilege(privilege);
		// wxUser.setCity(unionid);
		// System.out.println("获取到微信用户信息为：\n" + wxUser);
		// } else {
		// System.out.println("没有获取到微信用户信息");
		// System.out.println(userInfo);
		// }
		//
		// } else {
		// System.out.println(va);
		// JSONObject reObj = refreshAccessToken(refreshToken);
		// if (!reObj.has("errcode")) {
		// System.out.println(reObj);
		// accessToken = (String) reObj.get("access_token");
		// System.out.println("刷新后的access_token:" + accessToken);
		// openid = (String) reObj.get("openid");
		// System.out.println("刷新后的openid:" + openid);
		// JSONObject userInfo = getUserInfo(accessToken, openid);
		// if (!userInfo.has("errcode")) {
		// System.out.println(userInfo);
		// String openid2 = userInfo.getString("openid");
		// String nickname = userInfo.getString("nickname");
		// String sex = userInfo.getString("sex");
		// String province = userInfo.getString("province");
		// String city = userInfo.getString("city");
		// String headimgurl = userInfo.getString("headimgurl");
		// String privilege = userInfo.getString("privilege");
		// String unionid = userInfo.getString("unionid");
		// WXUserInfo wxUser = new WXUserInfo();
		// wxUser.setOpenId(openid2);
		// wxUser.setNickname(nickname);
		// wxUser.setSex(Integer.parseInt(sex));
		// wxUser.setProvince(province);
		// wxUser.setCity(city);
		// wxUser.setHeadImgUrl(headimgurl);
		// wxUser.setPrivilege(privilege);
		// wxUser.setCity(unionid);
		// System.out.println("获取到微信用户信息为：\n" + wxUser);
		// } else {
		// System.out.println("没有获取到微信用户信息");
		// System.out.println(userInfo);
		// }
		// } else {
		// System.out.println("刷新access_token失败");
		// System.out.println(reObj);
		// }
		// }
		//
		// } else {
		// System.out.println("access_token获取错误");
		// System.out.println(j);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return "welcome";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult list() {
		return null;
	}

	/*
	 * @RequestMapping(value="/customer/getRetrieveCode",method=RequestMethod. POST)
	 * 
	 * @ResponseBody public JsonResult getRetrieveCode(HttpServletRequest request,
	 * String phone, String imgcode, HttpSession session) {
	 * session.setAttribute("loginCode", "123"); imgcode = "123"; return
	 * userService.getRetrieveCode(request, phone, imgcode, session); }
	 */

	/**
	 * 获取access_token
	 * 
	 * @param code
	 * @return
	 */
	private JSONObject getAccessToken(String code) {
		url = url.replace("APPID", WechatPropUtil.prop.getProperty("wechat.app_id"));
		url = url.replace("SECRET", WechatPropUtil.prop.getProperty("wechat.app_secret"));
		url = url.replace("CODE", code);
		return WeixinUtil.httpRequest(url, "GET", "UTF-8");
	}

	/**
	 * 刷新access_token
	 * 
	 * @param code
	 * @return
	 */
	private JSONObject refreshAccessToken(String refreshToken) {
		url = url.replace("APPID", WechatPropUtil.prop.getProperty("wechat.app_id"));
		url = url.replace("REFRESH_TOKEN", refreshToken);
		return WeixinUtil.httpRequest(refreshUrl, "GET", "UTF-8");
	}

	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	private JSONObject getUserInfo(String accessToken, String openid) {
		url = url.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("OPENID", openid);
		return WeixinUtil.httpRequest(userInfoUrl, "GET", "UTF-8");
	}

	/**
	 * 验证token是否有效
	 * 
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	private JSONObject isTokenValid(String accessToken, String openid) {
		url = url.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("OPENID", openid);
		return WeixinUtil.httpRequest(validateUrl, "GET", "UTF-8");
	}
}
