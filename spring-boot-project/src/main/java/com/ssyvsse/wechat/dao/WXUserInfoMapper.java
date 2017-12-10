package com.ssyvsse.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssyvsse.wechat.entity.WXUserInfo;

/**
 * 微信用户处理的dao
 * 
 * @author llb
 *
 * @Date 2017年12月10日 下午9:16:15 
 */
@Repository
public interface WXUserInfoMapper extends JpaRepository<WXUserInfo, Integer>{
	
	WXUserInfo findByOpenid(String openid);
	
}
