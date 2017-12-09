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
public class WechtPropUtil {

	private static Logger logger = Logger.getLogger(WechtPropUtil.class);

	public static Properties prop;
	static{
		prop = new Properties();
		try {
			prop.load(new InputStreamReader(WechtPropUtil.class.getResourceAsStream("../../../../wechat/wechatDev.properties"), "UTF-8"));
		} catch (IOException e) {
			logger.error("读取配置文件wechatDev.properties报错");
			e.printStackTrace();
		}
	}
}
