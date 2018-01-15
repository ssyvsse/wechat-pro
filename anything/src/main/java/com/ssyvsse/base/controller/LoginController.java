package com.ssyvsse.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.base.service.LoginService;
import com.ssyvsse.util.vcode.Captcha;
import com.ssyvsse.util.vcode.GifCaptcha;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private IUserService userService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	@ResponseBody
	public JsonResult login(User user, HttpSession session, HttpServletRequest request) {
		return loginService.backLogin(user, session, request);
	}

	@RequestMapping(value = "getGifCode", method = RequestMethod.GET)
	public void getGifCode(HttpServletResponse response, HttpSession session) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/png");
			/**
			 * gif格式动画验证码 宽，高，位数。
			 */
			Captcha captcha = new GifCaptcha(146, 30, 6);
			captcha.out(response.getOutputStream());
			session.setAttribute("_code", captcha.text().toLowerCase());
		} catch (Exception e) {
			logger.error("获取验证码异常：" + e.getMessage());
		}
	}

	@PostMapping("/regist")
	@ResponseBody
	public JsonResult register(User user, HttpServletRequest request) {
		return userService.regist(user, request);
	}

	@GetMapping("/previlige/no")
	public String unauthorizedUrl() {
		return "unauthorized";
	}
}
