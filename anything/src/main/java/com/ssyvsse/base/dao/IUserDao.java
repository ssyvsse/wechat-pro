package com.ssyvsse.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.User;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
@Repository
public interface IUserDao extends IBaseDao<User,String>{

	User findById(String id);
	
	void deleteById(String id);
	
	User findByUserName(String userName);
	
	List<User> findByTelephone(String telephone);
	
	User findByNickName(String nickName);
}
