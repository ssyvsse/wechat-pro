package com.ssyvsse.wechat.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author llb
 *
 * @Date 2018年1月14日 上午9:00:03
 */
public class WXPropertiesUtil {

	private static Logger logger = Logger.getLogger(WXPropertiesUtil.class);

	public static String token;
	public static String app_id;
	public static String app_secret;
	public static String oauth_url;
	public static String encrypt_message;
	public static String encoding_aeskey;
	
	public static Properties prop;

	static {
		prop = new Properties();
		try {
			prop.load(new InputStreamReader(WXPropertiesUtil.class.getResourceAsStream("/wechat/wechat_dev.properties"),
					"UTF-8"));

			token = prop.getProperty("wechat.token").trim();
			app_id = prop.getProperty("wechat.app_id").trim();
			app_secret = prop.getProperty("wechat.app_secret").trim();
			oauth_url = prop.getProperty("wechat.oauth_url").trim();
			encrypt_message = prop.getProperty("wechat.encrypt_message").trim();
			encoding_aeskey = prop.getProperty("wechat.encoding_aeskey").trim();
		} catch (IOException e) {
			logger.error("读取配置文件wechatDev.properties报错");
			e.printStackTrace();
		}
	}

}
