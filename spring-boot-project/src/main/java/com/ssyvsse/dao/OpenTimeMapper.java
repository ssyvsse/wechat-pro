package com.ssyvsse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssyvsse.pojo.OpenTime;

/**
 * @author llb
 *
 * @Date 2017年12月11日
 */
public interface OpenTimeMapper {
	
	List<OpenTime> findByTypeid(@Param("typeid")int typid);
	
	void addPeriod(OpenTime openTime);
	
	int getNewNoByPeriodAndTypeId(@Param("period")String period,@Param("typeid")int typeid);
}
