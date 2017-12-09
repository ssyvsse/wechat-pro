package com.ssyvsse.base.dao;

import java.util.List;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.User;

/**
 * @author llb
 *
 * @Date 2017年11月29日 下午10:47:54
 */
public interface IUserDao extends IBaseDao<User, Integer> {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	User findByUserName(String userName);

	/**
	 * 查找所有用户
	 */
	List<User> findAll();

	/**
	 * 根据手机号查找用户
	 * 
	 * @param phone
	 * @return
	 */
	User findByTelephone(String phone);
}
