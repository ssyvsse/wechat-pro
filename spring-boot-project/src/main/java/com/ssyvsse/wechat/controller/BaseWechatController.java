package com.ssyvsse.wechat.controller;

import com.ssyvsse.base.controller.BaseController;
import com.ssyvsse.wechat.config.ApiConfig;
import com.ssyvsse.wechat.utils.WechatPropUtil;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class BaseWechatController extends BaseController {

	/**
	 * 获取API的配置
	 */
	ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		ac.setToken(WechatPropUtil.prop.getProperty("wechat.token").trim());
		ac.setAppId(WechatPropUtil.prop.getProperty("wechat.app_id").trim());
		ac.setAppSecret(WechatPropUtil.prop.getProperty("wechat.app_secret").trim());
		ac.setOauthUrl(WechatPropUtil.prop.getProperty("wechat.oauth_url").trim());
		ac.setEncryptMessage(Boolean.parseBoolean(WechatPropUtil.prop.getProperty("wechat.encrypt_message").trim()));
		ac.setEncodingAesKey(WechatPropUtil.prop.getProperty("wechat.encoding_aeskey").trim());
		return ac;
	}
}
