package com.ssyvsse.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.support.IBaseService;

/**
 * <p>
 * 用户服务类
 * <p>
 * 
 * @author llb
 *
 * @Date 2017年11月29日 下午10:57:50
 */
public interface IUserService extends IBaseService<User, Integer> {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	User findByUserName(String username);

	/**
	 * 增加或者修改用户
	 * 
	 * @param user
	 * @param request
	 * @param session
	 */
	void saveOrUpdate(User user, HttpServletRequest request, HttpSession session);

	/**
	 * 获取手机验证码
	 * 
	 * @param request
	 * @param phone
	 * @param imgcode
	 * @param session
	 * @return
	 */
	JsonResult getRetrieveCode(HttpServletRequest request, String phone, String imgcode, HttpSession session);
}
