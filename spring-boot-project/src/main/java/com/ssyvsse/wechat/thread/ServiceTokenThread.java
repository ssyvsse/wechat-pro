package com.ssyvsse.wechat.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.wechat.config.AccessToken;
import com.ssyvsse.wechat.utils.WechatPropUtil;
import com.ssyvsse.wechat.utils.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * 定时获取微信access_token的线程
 * 在WechatMpDemoApplication中注解@EnableScheduling，在程序启动时就开启定时任务。 每7200秒执行一次
 * 
 * @author llb
 *
 * @Date 2017年12月10日 下午3:19:44
 */
//@Component("ServiceTokenThread")
public class ServiceTokenThread {
	 private static Logger log = LoggerFactory.getLogger(ServiceTokenThread.class);
	    // 第三方用户唯一凭证
	    public static String appid = WechatPropUtil.app_id;
	    // 第三方用户唯一凭证密钥

	    public static String appsecret = WechatPropUtil.app_secret;
	    // 第三方用户唯一凭证
	    public static AccessToken accessToken = null;
	    
	    @Scheduled(fixedDelay = 7140000)
	    //7200秒执行一次
	    public void gettoken(){
	        accessToken=WeixinUtil.getAccessToken(appid,appsecret);
	        if(null!=accessToken){
	            log.info("成功获取accessToken:"+accessToken.getAccess_token());
	        }else {
	        	JSONObject jsonObject = WeixinUtil.httpRequest("https://www.2349m.com/fuwuhao/getAccessToken","GET",null);
	        	accessToken = new AccessToken();
	        	accessToken.setAccess_token(jsonObject.getJSONObject("data").getString("access_token"));
	        	accessToken.setExpiresIn(jsonObject.getJSONObject("data").getInt("expiresIn"));
	        	log.info("成功获取服务器中的:accessToken:"+accessToken.getAccess_token());
	        }
	    }
}
