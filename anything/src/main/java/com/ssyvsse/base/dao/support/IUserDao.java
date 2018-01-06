package com.ssyvsse.base.dao.support;

import java.util.List;

import com.ssyvsse.base.entity.User;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
public interface IUserDao extends IBaseDao<User,Integer>{

	User findById(String id);
	
	void deleteById(String id);
	
	User findByUserName(String userName);
	
	List<User> findByTelephone(String telephone);
}
