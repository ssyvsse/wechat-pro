package com.ssyvsse.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.ssyvsse.pojo.HomepageCommon;

/**
 * @author llb
 *
 * @Date 2018年1月29日 下午9:24:27
 */
public interface HomepageCommonMapper {

	@Select("select * from homepage_common where type='navigation' ")
	@ResultMap(value = "com.ssyvsse.dao.HomepageCommonMapper.homepageMap")
	List<HomepageCommon> findAllByNavigation();

	@Select("select * from homepage_common where type='carousel' and parent_id=6 ")
	@ResultMap(value = "com.ssyvsse.dao.HomepageCommonMapper.homepageMap")
	List<HomepageCommon> findCarouselByIndex();

	@Select("select * from homepage_common")
	@ResultMap(value = "com.ssyvsse.dao.HomepageCommonMapper.homepageMap")
	List<HomepageCommon> selectAll();

}
