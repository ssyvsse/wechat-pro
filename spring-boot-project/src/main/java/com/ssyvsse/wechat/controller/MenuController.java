package com.ssyvsse.wechat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.wechat.menu.MenuApi;
import com.ssyvsse.wechat.thread.TokenThread;

import net.sf.json.JSONObject;

/**
 * @author llb
 *
 * @Date 2017年12月10日 下午3:17:48 
 */
@Controller
@RequestMapping("/fuwuhao")
public class MenuController {
	@Value("${mybatis}")
	private String mybatis;
	 
	@RequestMapping(value = "menu/createMenu",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult createMenu(){
		int result = -1;
		result = MenuApi.createMenu(TokenThread.accessToken.getAccess_token());
		
		if(result==0) {
			return JsonResult.success("菜单创建成功");			
		}
		return JsonResult.failure("菜单创建失败");
	}
	
	@RequestMapping(value = "menu/seeMenuConfig",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult  seeMenuConfig(){
		JSONObject result = MenuApi.seeMenuConfig(TokenThread.accessToken.getAccess_token());
		if(result!=null) {
			return JsonResult.success("",result.toString());			
		}
		return JsonResult.failure("查看菜单失败");
	}
}
