package com.ssyvsse.base.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
	
	@Query(value="select * from tb_user where id=?1",nativeQuery = true)
	User findByPassword(String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update tb_user u set u.password=?1 where u.id=?2",nativeQuery=true)
	int updatePwd(String newPwd,String id);
}