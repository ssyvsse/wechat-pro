package com.ssyvsse.base.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ssyvsse.base.controller.support.BaseController;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午10:06:45 
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController{

	@RequestMapping("/index.html")
	public String index(){
		return "admin/index";
	}
	@RequestMapping("/welcome.html")
	public String welcom(){
		return "welcome";
	}
	@RequestMapping("/login.html")
	public String login(){
		return "admin/login";
	}
	@RequestMapping("/register.html")
	public String register(){
		return "admin/register";
	}
}
