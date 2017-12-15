package com.ssyvsse.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssyvsse.dao.PlatformMapper;
import com.ssyvsse.pojo.Cp_platform;
import com.ssyvsse.pojo.Platform;
import com.ssyvsse.service.PlatformService;

/***
 * 
 * @author lxq
 * @Date 2017年6月27日 下午3:50:03
 */
@Service
public class PlatformServiceImpl implements PlatformService{
	@Autowired
	private PlatformMapper platformMapper;
	
	@Cacheable(value="resourceCache",keyGenerator="myKey")
	@Override
	public List<Platform> getAllPlatForm() {
		return platformMapper.getAllPlatForm();
	}
	
	@Cacheable(value="resourceCache",keyGenerator="myKey")
	@Override
	public List<Cp_platform> getCpByPlatform(String platform) {
		return platformMapper.getCpByPlatform(platform);
	}

	@Cacheable(value="resourceCache",keyGenerator="myKey")
	@Override
	public Platform getPlatformByName(String name) {
		return platformMapper.getPlatformByName(name);
	}

}
