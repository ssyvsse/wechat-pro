package com.ssyvsse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssyvsse.pojo.Cp_platform;
import com.ssyvsse.pojo.Platform;

/**
 * 
 * @author 
 * @Date 2017年6月27日 下午3:52:13
 */
public interface PlatformMapper {
	/***
	 * 获取所有的抓取平台
	 * @return
	 */
	List<Platform> getAllPlatForm();
	
	/***
	 * 获取抓取平台下的所有高频彩票种类
	 * @param platform
	 * @return
	 */
	List<Cp_platform> getCpByPlatform(@Param("platform")String platform);
	
	
	Platform getPlatformByName(@Param("name")String name);
}
