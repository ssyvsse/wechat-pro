package com.ssyvsse.base.dao;

import java.util.List;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.User;

/**
 * @author llb
 *
 * @Date 2017年11月29日 下午10:47:54 
 */
public interface IUserDao extends IBaseDao<User,Integer>{
	
	User findByUserName(String userName);
	
	List<User> findAll();
	
	User findByTelephone(String phone);
}
