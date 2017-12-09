package com.ssyvsse.wechat.controller;

import com.ssyvsse.base.controller.BaseController;
import com.ssyvsse.wechat.config.ApiConfig;
import com.ssyvsse.wechat.utils.WechtPropUtil;

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
		ac.setToken(WechtPropUtil.prop.getProperty("wechat.token").trim());
		ac.setAppId(WechtPropUtil.prop.getProperty("wechat.app_id").trim());
		ac.setAppSecret(WechtPropUtil.prop.getProperty("wechat.app_secret").trim());
		ac.setOauthUrl(WechtPropUtil.prop.getProperty("wechat.oauth_url").trim());
		ac.setEncryptMessage(Boolean.parseBoolean(WechtPropUtil.prop.getProperty("wechat.encrypt_message").trim()));
		ac.setEncodingAesKey(WechtPropUtil.prop.getProperty("wechat.encoding_aeskey").trim());
		return ac;
	}
}
