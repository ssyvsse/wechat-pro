package com.ssyvsse.wechat.menu;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssyvsse.wechat.utils.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class MenuApi {

	private static Logger log = LoggerFactory.getLogger(MenuApi.class);// 日记

	// 菜单创建（POST） 限1000（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 菜单查询（POST） 限10000（次/天）
	public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	// 菜单删除（POST） 限1000（次/天）
	public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(String accessToken) {

		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = null;
		try {
			jsonMenu = JSONObject.fromObject(MenuManage.getMenu()).toString();
			log.info("jsonMenu的josn" + jsonMenu);
			// 调用接口创建菜单
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);

			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getInt("errcode");
					log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

    /** 
     * 创建菜单 
     *  
     * @param menu 菜单实例 
     * @param accessToken 有效的access_token 
     * @return 0表示成功，其他值表示失败 
     */ 
    public static int createMenu2(String accessToken) {  
    	
    	int result = 0;  
    	
    	// 拼装创建菜单的url  
    	String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
    	// 将菜单对象转换成json字符串  
    	String jsonMenu;
    	try {
    		jsonMenu = JSONObject.fromObject(MenuManage.getMenu()).toString();
    		// 调用接口创建菜单  
    		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);  
    		log.info("微信菜单创建:"+jsonMenu);
    		
    		if (null != jsonObject) {  
    			if (0 != jsonObject.getInt("errcode")) {  
    				result = jsonObject.getInt("errcode");  
    				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
    			}  
    		}  
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	
    	return result;  
    }  
    
    
    public static JSONObject seeMenuConfig(String accessToken) {  
    	
    	String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);  
    	// 调用接口查看菜单  
    	JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
    	if(jsonObject.has("errcode")){
    		log.error("微信菜单查询失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
    		return null;
    	}
    	log.info(jsonObject.toString());
    	return jsonObject;  
    }  

	public int deleteMenu(String accessToken) {
		
		return 0;
	}

}
