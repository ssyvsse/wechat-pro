package com.ssyvsse.wechat.utils;

import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;



/**
 * @author llb
 *
 * @Date 2017年11月29日 下午9:06:45 
 */
public class WechatPropUtil {
	private static Logger logger = Logger.getLogger(WechatPropUtil.class);

	public static Properties prop;
	
	static {
		prop = new Properties();
		try {
			prop.load(new InputStreamReader(WechatPropUtil.class.getResourceAsStream("../../../../wechat/wechatDev.properties")));
		} catch (Exception e) {
			logger.error("读取配置文件wechatDev.properties报错");
			e.printStackTrace();
		}
	}
}
