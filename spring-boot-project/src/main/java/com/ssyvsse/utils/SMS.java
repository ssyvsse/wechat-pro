package com.ssyvsse.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ssyvsse.base.common.JsonResult;

/**
 * @author llb
 *
 * @Date 2017年12月17日 下午9:13:05 
 */
public class SMS {
	private static String postUrl;
	private static String sname;
	private static String spwd;
	/**
	 * 行业通知类（如会议通知，活动通知，到货通知等）
	 */
	public static final String NOTICE_SPRDID = "1012808";
	/**
	 * 会员营销类（如对会员推销一些产品的信息）
	 */
	public static final String MAKETING_SPRDID = "1012812";
	/**
	 * 会员通标准版 是用手机号码发送的，不推荐使用
	 */
	public static final String STANDARD_SPRDID = "1012813";
	/**
	 * 行业触发短信（验证码等触发类产品）
	 */
	public static final String TRIGGER_SPRDID = "1012818";
	/**
	 * 行业触发短信（验证码等触发类产品,群发）
	 */
	public static final String TRIGGER_SPRDID_GROUP = "1012888";

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("key");
		postUrl = bundle.getString("postUrl");
		sname = bundle.getString("sname");
		spwd = bundle.getString("spwd");
	}

	public static JsonResult Send(String sprdid, String sdst, String smsg) {
	//	if ("Linux".equals(System.getProperty("os.name"))) {
			String status="-1";
			String msg="发送失败";
			try {
				// 发送POST请求
				String postData = "sname=" + sname + "&spwd=" + spwd
						+ "&scorpid=&sprdid=" + sprdid + "&sdst=" + sdst + "&smsg="
						+ java.net.URLEncoder.encode(smsg, "utf-8");
				URL url = new URL(postUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setUseCaches(false);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length", "" + postData.length());
				OutputStreamWriter out = new OutputStreamWriter(
						conn.getOutputStream(), "UTF-8");
				out.write(postData);
				out.flush();
				out.close();
				// 获取响应状态
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					System.out.println("connect failed!");
					return JsonResult.failure(msg);
				}
				// 获取响应内容体
				String line, result = "";
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf-8"));
				while ((line = in.readLine()) != null) {
					result += line + "\n";
				}
				in.close();
				Document doc = DocumentHelper.parseText(result);
	            //指向根节点
	            Element root = doc.getRootElement();
	            List<Element> elements = root.elements();
	            for (Element element : elements) {
	            	if(element.getName().equals("State")){
	            		if(element.getStringValue().equals("0")){
	            			status="0";
	            		}
	            	}
	            	if(element.getName().equals("MsgState")){
	            		msg=element.getStringValue();
	            	}
	            }
	            if(status.equals("0")){
	            	return JsonResult.success(msg);
	            }else if(status.equals("-1")){
	            	return JsonResult.failure(msg);
	            }
			} catch (IOException | DocumentException e) {
				e.printStackTrace(System.out);
			}
			return JsonResult.failure(msg);
//		} else {
//			return JsonResult.success("提交成功");
//		}
	}
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	System.out.println(Send(SMS.TRIGGER_SPRDID, "15980820315",
				"验证码：4321。您正在注册彩票网账户，验证码五分钟内有效。【彩票网】"));
	}
}
