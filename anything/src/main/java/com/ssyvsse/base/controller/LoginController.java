package com.ssyvsse.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.base.service.LoginService;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult login(User user,HttpSession session,HttpServletRequest request) {
		return loginService.backLogin(user, session,request);
	}
	
	@PostMapping("/regist")
	@ResponseBody
	public JsonResult register(User user,HttpServletRequest request) {
		return userService.regist(user, request);
	}
}
