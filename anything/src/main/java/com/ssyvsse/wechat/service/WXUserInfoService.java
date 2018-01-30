package com.ssyvsse.wechat.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssyvsse.wechat.pojo.WXUserInfo;

/**
 * @author llb
 *
 * @Date 2018年1月29日
 */
@Repository
public interface WXUserInfoService extends JpaRepository<WXUserInfo, Integer> {

}
