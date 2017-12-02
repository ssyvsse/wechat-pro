package com.ssyvsse.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author llb
 *
 * @Date 2017年12月2日 上午11:01:11 
 */
public class NetUtils {
	/***
	 * 封装http get请求
	 * @param urlAll
	 * @param charset
	 * @return
	 */
	public static String get(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	 * 将URL串封装为string流
	 * @param url
	 * @return
	 */
	public static String getURLAll(String url){
		return new StringBuffer(url).toString();
	}
	
	
	
	
	public static String baiduSpiderURL(String website,Integer baiduID){
		String url = website + baiduID;
		return new StringBuffer(url).toString();
	}
}
