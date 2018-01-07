package com.ssyvsse.base.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午10:06:45
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {

	@RequestMapping("/index.html")
	public String index() {
		return "admin/index";
	}

	@RequestMapping("/welcome.html")
	public String welcom() {
		return "admin/welcome";
	}

	@RequestMapping("/login.html")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/register.html")
	public String register() {
		return "admin/register";
	}

	@GetMapping("/admin-list.html")
	public String adminList() {
		return "/admin/admin-list";
	}

	@GetMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return redirect("/admin/index.html");
	}
 
	public String redirect(String path) {
		return "redirect:" + path;
	}
}
