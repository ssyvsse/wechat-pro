package com.ssyvsse.base.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.User;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
@Repository
@Transactional
public interface IUserDao extends IBaseDao<User, String> {

	User findById(String id);

	void deleteById(String id);

	User findByUserName(String userName);

	List<User> findByTelephone(String telephone);

	User findByNickName(String nickName);

	@Query(value = "select * from tb_user where id=?1", nativeQuery = true)
	User findByPassword(String id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update tb_user u set u.password=?1 where u.id=?2", nativeQuery = true)
	int updatePwd(String newPwd, String id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update tb_user set secret=:secret where user_name=:userName", nativeQuery = true)
	void updateSecretByUsername(@Param("userName") String userName, @Param("secret") String secret);

	@Modifying(clearAutomatically = true)
	@Query(value = "update tb_user set google_open=:google_open where id=:id", nativeQuery = true)
	void updateGoogle_openById(@Param("id") String id, @Param("google_open") String google_open);

	@Modifying(clearAutomatically = true)
	@Query(value = "update tb_user set binding=:binding where id=:id", nativeQuery = true)
	void updateBindingById(@Param("id") String id, @Param("binding") String binding);
}
