package com.ssyvsse.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.ssyvsse.base.common.JsonResult;

/**
 * @author llb
 *
 * @Date 2017年11月30日 下午11:37:04 
 */
public class CPSMS {
private static String login;
	
	private static String register;
	
	private static String retrieve;
	
	private static String sendPassword;
	
	private static Integer interval;
	
	private static Integer max;
	
	private static int length;

	

	private static Map<String, Long> phoneMap;
	private static Map<String, Integer> ipMap;
	static {
		ResourceBundle bundle = ResourceBundle
				.getBundle("SMS_Template");
		login = bundle.getString("login");
		register = bundle.getString("register");
		retrieve = bundle.getString("retrieve");
		sendPassword = bundle.getString("sendPassword");
		interval = Integer.valueOf(bundle.getString("interval")) * 1000;
		max = Integer.valueOf(bundle.getString("max"));
		length = Integer.valueOf(bundle.getString("length"));
		phoneMap = new HashMap<String, Long>();
		ipMap = new HashMap<String, Integer>();
	}

	public static JsonResult sendLogin(HttpServletRequest req, String phone) {
		System.out.println(login);
		return send(req, phone, login);
	}

	public static JsonResult sendRegister(HttpServletRequest req, String phone) {
		return send(req, phone, register);
	}

	public static JsonResult sendRetrieve(HttpServletRequest req, String phone) {
		
		return send(req, phone, retrieve);
	}
	
	/**
	 * 尊敬的：188****8888 恭喜您成功注册玖彩网会员，您的初始密码为：{初始密码}，
	 * 请尽快登录并修改您的密码。祝您生活愉快！【玖彩网】
	 * @param phone
	 * @param password
	 * @return
	 */
	public static JsonResult send2( String phone,String password){
		String username=phone.substring(0, 3) + "****" +  phone.substring(7);
		String msg=sendPassword.replaceAll("#用户#", username);
		msg=msg.replaceAll("#初始密码#", password);
		JsonResult json=SMS.Send(SMS.TRIGGER_SPRDID_GROUP, phone, msg);
		return json;
	}
	
	
	private static JsonResult send(HttpServletRequest req, String phone,String smsg){
		if(validate(req,phone)){
			String code=MyRandom.randomCode(length);
			String msg=smsg.replaceAll("#验证码#", code);
			JsonResult json=SMS.Send(SMS.TRIGGER_SPRDID_GROUP, phone, msg);
			//json.setData(code);
			System.out.println("LoginCode==>>>"+code);
			req.getSession().setAttribute("LoginCode", code);
			req.getSession().setAttribute("RetrieveCode", code);
			return json;
		}
		return JsonResult.failure("发送请求过快，请稍后再试");
	}
	
	

	/**
	 * 验证是否能发送
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param phone
	 *            手机号
	 * @return 是否能发送
	 */
	public static boolean validate(HttpServletRequest req, String phone) {
		String ip=getRemoteHost(req);
		if(validateIP(ip)&&validatePhone(phone)){
			return true;
		}
		return false;
	}

	/**
	 * 校验手机号码
	 * 
	 * @param phone
	 * @return
	 */
	private static boolean validatePhone(String phone) {
		Long time=phoneMap.get(phone);
		if(time==null){
			phoneMap.put(phone, System.currentTimeMillis());
			return true;
		}else{
			if((System.currentTimeMillis()-time)>interval){
				phoneMap.put(phone,System.currentTimeMillis());
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验IP地址
	 * 
	 * @param ip
	 * @return
	 */
	private static boolean validateIP(String ip) {
		Integer count = ipMap.get(ip);
		if (count == null) {
			ipMap.put(ip, 1);
			return true;
		} else {
			if(count<max){
				ipMap.put(ip, count+1);
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取真实IP地址
	 * 
	 * @param request
	 * @return 真实IP地址
	 */
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}
	
	
	public static void cleanMap(){
		ipMap.clear();
	}

	
	public static void main(String[] args) {
		String code=MyRandom.randomCode(length);
		String smsg=login.replaceAll("#验证码#", code);
		System.out.println(smsg);
	}
}
