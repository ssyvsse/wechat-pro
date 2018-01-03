package com.ssyvsse.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
@Controller
public class LoginController {
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult login() {
		return JsonResult.success();
	}
}
