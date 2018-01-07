package com.ssyvsse.base.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
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
		if ("background".equals(user.getLoginType())) {
			try {
				Subject subject = SecurityUtils.getSubject();
				DefaultUsernamepasswordToken token = new DefaultUsernamepasswordToken(user.getUserName(),
						user.getPassword(), user.getLoginType());
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
					userDao.save(loginUser);
				}
				logger.info("成功登录后台...");
				return JsonResult.success();
			} catch (AuthenticationException e) {
				logger.info("登录异常!");
				logger.info(e.getCause().getMessage());
				return JsonResult.failure(e.getMessage());
			}
		} else {
			return JsonResult.success();
		}
	}

}
