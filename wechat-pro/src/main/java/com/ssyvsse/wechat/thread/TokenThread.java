package com.ssyvsse.wechat.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.wechat.config.AccessToken;
import com.ssyvsse.wechat.config.ApiConfig;
import com.ssyvsse.wechat.config.ApiConfigKit;
import com.ssyvsse.wechat.service.MenuApi;
import com.ssyvsse.wechat.utils.WechatPropUtil;
import com.ssyvsse.wechat.utils.WeixinUtil;

/**
 * 定时获取微信access_token的线程
 * 在WechatMpDemoApplication中注解@EnableScheduling，在程序启动时就开启定时任务。 每7200秒执行一次
 */
//@Component("tokenThread")
//@EnableScheduling
public class TokenThread {
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	// 第三方用户唯一凭证
	public static String appid = WechatPropUtil.prop.getProperty("wechat.app_id").trim();
	// 第三方用户唯一凭证密钥

	public static String appsecret = WechatPropUtil.prop.getProperty("wechat.app_secret").trim();
	// 第三方用户唯一凭证
	public static AccessToken accessToken = null;

	public static int sign = 0;

	@Scheduled(fixedDelay = 7195000) // fixedRate fixedDelay
	// 7200秒执行一次
	public void getAccessToken() {
		accessToken = WeixinUtil.getAccessToken(appid, appsecret);

		if (null != accessToken) {
			ApiConfig ac = new ApiConfig();
			ac.setToken(WechatPropUtil.prop.getProperty("wechat.token").trim());
			ac.setAppId(WechatPropUtil.prop.getProperty("wechat.app_id").trim());
			ac.setAppSecret(WechatPropUtil.prop.getProperty("wechat.app_secret").trim());
			ac.setOauthUrl(WechatPropUtil.prop.getProperty("wechat.oauth_url").trim());
			ac.setEncryptMessage(
					Boolean.parseBoolean(WechatPropUtil.prop.getProperty("wechat.encrypt_message").trim()));
			ac.setEncodingAesKey(WechatPropUtil.prop.getProperty("wechat.encoding_aeskey").trim());
			ApiConfigKit.setThreadLocalApiConfig(ac);
			int result = MenuApi.createMenu(accessToken.getAccess_token());
    		sign=1;
    		if(result==0) {
    			log.info("菜单创建成功"); 
    		}
			log.info("获取成功，accessToken:" + accessToken.getAccess_token());
		} else {
			log.error("获取accessToken失败");
		}
	}
}
