package com.ssyvsse.wechat.controller;

import com.ssyvsse.wechat.config.ApiConfig;
import com.ssyvsse.wechat.utils.WXPropertiesUtil;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午8:09:32 
 */
public class BaseWechatController {

	/**
	 * 获取API的配置
	 * @return
	 */
	protected ApiConfig getApiConfig(){
		ApiConfig apiConfig = new ApiConfig();
		apiConfig.setToken(WXPropertiesUtil.prop.getProperty("wechat.token").trim());
		apiConfig.setAppId(WXPropertiesUtil.prop.getProperty("wechat.app_id").trim());
		apiConfig.setAppSecret(WXPropertiesUtil.prop.getProperty("wechat.app_secret").trim());
		apiConfig.setOauthUrl(WXPropertiesUtil.prop.getProperty("wechat.oauth_url").trim());
		apiConfig.setEncryptMessage(Boolean.parseBoolean(WXPropertiesUtil.prop.getProperty("wechat.encrypt_message").trim()));
		apiConfig.setEncodingAesKey(WXPropertiesUtil.prop.getProperty("wechat.encoding_aeskey").trim());
		return apiConfig;
	}
}
