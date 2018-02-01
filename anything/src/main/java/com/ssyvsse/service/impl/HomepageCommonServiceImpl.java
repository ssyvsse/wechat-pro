package com.ssyvsse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssyvsse.dao.HomepageCommonMapper;
import com.ssyvsse.pojo.HomepageCommon;
import com.ssyvsse.service.HomepageCommonService;

/**
 * @author llb
 *
 * @Date 2018年1月29日 下午9:24:08
 */
@Service
public class HomepageCommonServiceImpl implements HomepageCommonService {

	@Autowired
	private HomepageCommonMapper homepageCommonMapper;

	@Override
	@Cacheable(value = "resourceCache", keyGenerator = "myKey")
	public List<HomepageCommon> findAllByNavigation() {
		return homepageCommonMapper.findAllByNavigation();
	}

	@Override
	@Cacheable(value = "resourceCache", keyGenerator = "myKey")
	public List<HomepageCommon> findCarouselByIndex() {
		return homepageCommonMapper.findCarouselByIndex();
	}

	@Override
	public List<HomepageCommon> selectAll() {
		return homepageCommonMapper.selectAll();
	}

}
