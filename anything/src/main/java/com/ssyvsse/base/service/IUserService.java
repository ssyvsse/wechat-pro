package com.ssyvsse.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.dao.IUserDao;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.support.IBaseService;

/**
 * @author llb
 *
 * @Date 2018年1月5日
 */
public interface IUserService extends IBaseService<User, String> {

	/**
	 * 添加后台用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	public JsonResult regist(User user, HttpServletRequest request);

	/**
	 * 增加或修改用户
	 * 
	 * @param user
	 * @param request
	 * @param session
	 */
	void saveOrUpdate(User user, HttpServletRequest request, HttpSession session);

	/**
	 * 给用户分配角色
	 * 
	 * @param id
	 *            用户id
	 * @param roleIds
	 *            角色ids
	 * @param request
	 */
	void grant(String id, String[] roleIds, HttpServletRequest request);

	/**
	 * 根据昵称查找用户
	 * 
	 * @param nickName
	 * @return
	 */
	public User findByNiceName(String nickName);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);

	/**
	 * 获取UserDao
	 * 
	 * @return
	 */
	public IUserDao getUserDao();

	/**
	 * 根据id查询密码
	 * 
	 * @param id
	 * @return
	 */
	public User findByPassword(String id);

	/**
	 * 后台修改密码
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public int updatePwd(String id, String password);
}
