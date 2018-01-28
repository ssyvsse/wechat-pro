package com.ssyvsse.wechat.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.wechat.config.ApiConfig;
import com.ssyvsse.wechat.config.ApiConfigKit;
import com.ssyvsse.wechat.mapper.AccessTokenMapper;
import com.ssyvsse.wechat.pojo.AccessToken;
import com.ssyvsse.wechat.utils.WXPropertiesUtil;
import com.ssyvsse.wechat.utils.WXUtil;

/**
 * 定时获取微信access_token的线程
 * 在WechatMpDemoApplication中注解@EnableScheduling，在程序启动时就开启定时任务。 每7200秒执行一次
 * 
 * @author llb
 *
 * @Date 2018年1月14日 上午8:58:43
 */
@Component("tokenThread")
@EnableScheduling
public class TokenThread {

	private static Logger log = LoggerFactory.getLogger(TokenThread.class);

	/**
	 * 第三方用户唯一凭证
	 */
	public static String appid = WXPropertiesUtil.prop.getProperty("wechat.app_id").trim();

	/**
	 * 第三方用户唯一凭证密钥
	 */
	public static String appsecret = WXPropertiesUtil.prop.getProperty("wechat.app_secret").trim();

	/**
	 * 第三方用户唯一凭证
	 */
	public static AccessToken accessToken = null;

	public static int sign = 0;

	@Autowired
	private AccessTokenMapper accessTokenMapper;

	/**
	 * 7200秒执行一次
	 */
	@Scheduled(fixedDelay = 7195000)
	public void getAccessToken() {
		accessToken = WXUtil.getAccessToken(appid, appsecret);
		if (null != accessToken) {
			if (sign == 0) {
				ApiConfig ac = new ApiConfig();
				ac.setToken(WXPropertiesUtil.prop.getProperty("wechat.token").trim());
				ac.setAppId(WXPropertiesUtil.prop.getProperty("wechat.app_id").trim());
				ac.setAppSecret(WXPropertiesUtil.prop.getProperty("wechat.app_secret").trim());
				ac.setOauthUrl(WXPropertiesUtil.prop.getProperty("wechat.oauth_url").trim());
				ac.setEncryptMessage(
						Boolean.parseBoolean(WXPropertiesUtil.prop.getProperty("wechat.encrypt_message").trim()));
				ac.setEncodingAesKey(WXPropertiesUtil.prop.getProperty("wechat.encoding_aeskey").trim());
				ApiConfigKit.setThreadLocalApiConfig(ac);
				// int result =
				// MenuApi.createMenu(accessToken.getAccess_token());
				// sign = 1;
				// if (result == 0) {
				// log.info("菜单创建成功");
				// }
			}
			log.info("获取成功，accessToken:" + accessToken.getAccess_token());
			try {
				AccessToken dbAccessToken = accessTokenMapper.findByType("wx_access_token");
				if (dbAccessToken == null) {
					accessToken.setType("wx_access_token");
					accessTokenMapper.saveAndFlush(accessToken);
				} else {
					dbAccessToken.setAccess_token(accessToken.getAccess_token());
					dbAccessToken.setExpiredTime(accessToken.getExpiredTime());
					accessTokenMapper.saveAndFlush(dbAccessToken);
				}
				log.info("access_token保存成功");
			} catch (Exception e) {
				log.error("access_token保存失败");
			}
		} else {
			log.error("获取accessToken失败");
		}
	}

	// private void outputAccessToken(AccessToken accessToken) throws
	// IOException {
	// File file = new File("temp");
	// if(!file.exists()) {
	// file.mkdirs();
	// }
	// log.info("文件所在路径为:"+file.getAbsolutePath()+"\\"+DateUtils.getToday()+"
	// access_token.txt");
	// FileWriter fw = new FileWriter(file+"/"+DateUtils.getToday()+"
	// access_token.txt",true);
	//
	// BufferedWriter bw = new BufferedWriter(fw);
	// bw.write(DateUtils.getNow());
	// bw.newLine();
	// bw.write("access_token=" + accessToken.getAccess_token());
	// bw.newLine();
	// bw.write("expired_time=" +
	// DateUtils.formatDate(DateUtils.stampToDate(""+accessToken.getExpiredTime())));
	// bw.newLine();
	// bw.newLine();
	// bw.close();
	// fw.close();
	// }

}
