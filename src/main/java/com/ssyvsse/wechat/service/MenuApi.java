package com.ssyvsse.wechat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssyvsse.wechat.controller.menu.MenuManage;
import com.ssyvsse.wechat.utils.WeixinUtil;

import net.sf.json.JSONObject;

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
     * @param menu 菜单实例 
     * @param accessToken 有效的access_token 
     * @return 0表示成功，其他值表示失败 
     */ 
    public static int createMenu(String accessToken) {  
    	
        int result = 0;  

        // 拼装创建菜单的url  
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
        // 将菜单对象转换成json字符串  
        String jsonMenu = JSONObject.fromObject(MenuManage.getMenu()).toString();
        log.info("jsonMenu的josn"+jsonMenu);
        // 调用接口创建菜单  
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);  

        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
        
        return result;  
    }  

	public int deleteMenu(String accessToken) {
		
		return 0;
	}
	
}
