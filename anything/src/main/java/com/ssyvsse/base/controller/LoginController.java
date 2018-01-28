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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.base.service.LoginService;
import com.ssyvsse.util.GoogleAuthenticator;
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
			Captcha captcha = new GifCaptcha(146, 30, 4);
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
	
	
	/**
	 * 重置谷歌绑定
	 */
	@RequestMapping("/admin/binding")
	@ResponseBody
	public void binding(@RequestParam("id") String id) {
		String binding = "未绑定";
		loginService.updateBindingById(id, binding);
	}

	/**
	 * 修改是否使用谷歌验证
	 */
	@RequestMapping("/admin/googleOpen")
	@ResponseBody
	public void googleOpen(@RequestParam("id") String id, @RequestParam("google_open") String google_open) {
		loginService.updateGoogle_openById(id, google_open);
	}

	/**
	 * 生成密钥
	 */
	@RequestMapping("/admin/getSecret")
	@ResponseBody
	public JsonResult genSecretTest(HttpSession session) {
		User user = (User) session.getAttribute("user");
		String userName = user.getUserName();
		// 生成密钥
		String secret = GoogleAuthenticator.generateSecretKey();
		loginService.updateSecretByUsername(userName, secret);
		String binding = "绑定";
		loginService.updateBindingById(user.getId().toString(), binding);
		// 把这个qrcode生成二维码，用google身份验证器扫描二维码就能添加成功
		String qrcode = GoogleAuthenticator.getQRBarcode(userName, secret);
		logger.info("qrcode:" + qrcode + ",key:" + secret);
		return JsonResult.success("获取成功", qrcode);
	}

	/**
	 * google验证
	 */
	@RequestMapping("/admin/googleCode")
	@ResponseBody
	public JsonResult googleCode(@RequestParam("googleCode") Long googleCode, HttpSession session) {
		User user = (User) session.getAttribute("user");
		User user1 = loginService.findUserByUsername(user.getUserName());
		String secret = user1.getSecret();
		long t = System.currentTimeMillis();
		GoogleAuthenticator ga = new GoogleAuthenticator();
		ga.setWindowSize(3);
		boolean r = ga.check_code(secret, googleCode, t);
		logger.info("检查code是否正确？" + r);
		if (r) {
			session.setAttribute("googleCode", googleCode);
			return JsonResult.success("验证成功");
		} else {
			return JsonResult.failure("验证失败");
		}
	}
}
