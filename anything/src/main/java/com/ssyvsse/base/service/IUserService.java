package com.ssyvsse.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.dao.support.IUserDao;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.support.IBaseService;

/**
 * @author llb
 *
 * @Date 2018年1月5日
 */
public interface IUserService extends IBaseService<User, String> {
	
	public JsonResult regist(User user,HttpServletRequest request);
	
	public IUserDao getUserDao();
	
	public JsonResult findByUserNameAndPassword(String userName,String password,HttpSession session);

}
