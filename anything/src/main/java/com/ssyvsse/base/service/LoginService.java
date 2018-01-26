package com.ssyvsse.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.entity.User;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午3:33:37 
 */
public interface LoginService {
	/**
	 * 后台登录
	 * 
	 * @param user
	 * @param session
	 * @param request
	 * @return
	 */
	public JsonResult backLogin(User user, HttpSession session, HttpServletRequest request);
	
	public void updateSecretByUsername(String userName, String secret);

	public User findUserByUsername(String userName);

	public void updateGoogle_openById(String id, String google_open);

	public void updateBindingById(String id, String binding);
}
