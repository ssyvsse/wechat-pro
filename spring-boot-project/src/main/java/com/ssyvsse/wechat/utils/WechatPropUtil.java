package com.ssyvsse.wechat.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class WechatPropUtil {

	private static Logger logger = Logger.getLogger(WechatPropUtil.class);

	//微信公众号配置
	public static String token;
	//public static String original_id;
	public static String app_id;
	public static String app_secret;
	public static String oauth_url;
	public static String encrypt_message;
	public static String encoding_aeskey;
	
/*	//开放者平台中网站应用的配置
	public static String websiteApp_id;
	public static String websiteApp_secret;
	//#开放者平台中移动应用的配置
	public static String mobileApp_id;
	public static String mobileApp_secret;
	
	//开奖查询按钮
	public static String mainBtn1;
	
	public static String mainBtn2;
	public static String btn21Name;  //查询
	public static String btn22Name;  //配置*/
	public static String btn22Url;
	public static String btn23Url;
	/*	public static String btn23Name;  //玩法说明
	
	public static String mainBtn3;
	public static String btn31Name; //访问网站
	public static String btn31Url;
	public static String btn32Name;  //访问论坛
	public static String btn32Url;
	public static String btn33Name;  //账户绑定
	public static String btn33Url;*/
	
	public static Properties prop;
	static{
		prop = new Properties();
		try {
			prop.load(new InputStreamReader(WechatPropUtil.class.getResourceAsStream("../../../../wechat/wechatDev.properties"), "UTF-8"));
			
			token= prop.getProperty("wechat.token").trim();
			app_id = prop.getProperty("wechat.app_id").trim();
			app_secret = prop.getProperty("wechat.app_secret").trim();
			oauth_url= prop.getProperty("wechat.oauth_url").trim();
			encrypt_message= prop.getProperty("wechat.encrypt_message").trim();
			encoding_aeskey= prop.getProperty("wechat.encoding_aeskey").trim();
			btn22Url= prop.getProperty("wechat.btn22Url").trim();
			btn23Url= prop.getProperty("wechat.btn23Url").trim();
		} catch (IOException e) {
			logger.error("读取配置文件wechatDev.properties报错");
			e.printStackTrace();
		}
	}
}
