package com.ssyvsse.service;

import java.util.List;

import com.ssyvsse.pojo.HomepageCommon;

/**
 * @author llb
 *
 * @Date 2018年1月29日 下午9:21:09
 */
public interface HomepageCommonService {

	List<HomepageCommon> findAllByNavigation();

	List<HomepageCommon> findCarouselByIndex();
	
	List<HomepageCommon> selectAll();
}
