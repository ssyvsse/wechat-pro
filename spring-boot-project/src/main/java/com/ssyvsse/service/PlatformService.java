package com.ssyvsse.service;

import java.util.List;

import com.ssyvsse.pojo.Cp_platform;
import com.ssyvsse.pojo.Platform;

/***
 * 抓取数据的平台Service
 * @author
 * @Date 2017年6月27日 下午3:39:27
 */
public interface PlatformService {
	/***
	 * 获取所有的抓取平台
	 * @return
	 */
	List<Platform> getAllPlatForm();
	
	/***
	 * 获取抓取平台下的所有彩票种类
	 * @param platform
	 * @return
	 */
	List<Cp_platform> getCpByPlatform(String platform);
	
	/***
	 * 根据平台的名称获取平台的开奖信息
	 * @param name
	 * @return
	 */
	Platform getPlatformByName(String name);
}
