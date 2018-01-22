package com.ssyvsse.base.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.config.shiro.DefaultUsernamepasswordToken;
import com.ssyvsse.base.dao.IUserDao;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.LoginService;
import com.ssyvsse.util.IpUtils;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午3:34:44
 */
@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private IUserDao userDao;

	@Override
	public JsonResult backLogin(User user, HttpSession session, HttpServletRequest request) {
		String imgCode = request.getParameter("imgCode");
		if (imgCode != null && !"".equals(imgCode)) {
			if (checkImgCode(imgCode, session)) {
				if ("background".equals(user.getLoginType())) {
					try {
						Subject subject = SecurityUtils.getSubject();
						if (subject != null) {
							PrincipalCollection principalCollection = subject.getPrincipals();
							if (principalCollection != null) {
								for (Object object : principalCollection) {
									System.out.println("---" + object.toString());
								}
							} else {
								System.out.println("principals is null");
							}
						} else {
							System.out.println("subject is null");
						}
						boolean rememberMe = false;
						if(request.getParameter("rememberMe")!=null&&"true".equals((String)request.getParameter("rememberMe"))){
							rememberMe = true;
						}
						DefaultUsernamepasswordToken token = new DefaultUsernamepasswordToken(user.getUserName(),
								user.getPassword(), user.getLoginType());
						if(token.isRememberMe()){
							if(subject.isAuthenticated()){
								logger.info("成功登录后台...");
								return JsonResult.success();
							}
						}else{
							if(rememberMe){
								token.setRememberMe(true);
							}
						}
						

						subject.login(token);
						logger.info("后台用户登录token:" + token.toString());

						/*
						 * logger.info("token.getPrincipal() = " +
						 * subject.getPrincipal() + "   isTrue = " +
						 * (subject.getPrincipal() instanceof User));
						 */
						logger.info("user.id = " + ((User) subject.getPrincipal()).getId());
						session.setAttribute("user", subject.getPrincipal());
						User loginUser = (User) subject.getPrincipal();
						if (loginUser != null) {
							loginUser.setLast_login_time(new Date());
							String ip = IpUtils.getRemoteHost(request);
							loginUser.setUser_ip(ip);
							userDao.save(loginUser);
						}
						logger.info("成功登录后台...");
						return JsonResult.success();
					} catch (AuthenticationException e) {
						logger.info("登录异常!");
						logger.info(e.getCause().getMessage());
						return JsonResult.failure("用户名或者密码错误!");
					}
				} else {
					return JsonResult.success();
				}
			} else {
				return JsonResult.failure("验证码错误!");
			}
		} else {
			return JsonResult.failure("验证码错误.");
		}
	}

	boolean checkImgCode(String imgCode, HttpSession session) {
		String $code = (String) session.getAttribute("_code");
		if ($code == null) {
			return false;
		} else {
			if (!$code.equals(imgCode)) {
				return false;
			} else {
				return true;
			}
		}
	}

}
