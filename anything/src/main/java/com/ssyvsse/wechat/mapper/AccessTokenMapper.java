package com.ssyvsse.wechat.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssyvsse.wechat.pojo.AccessToken;

/**
 * @author llb
 *
 * @Date 2018年1月29日 上午12:10:20
 */
@Repository
public interface AccessTokenMapper extends JpaRepository<AccessToken, Integer> {

	AccessToken findByType(String type);
	
}
