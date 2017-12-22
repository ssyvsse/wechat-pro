package com.ssyvsse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssyvsse.pojo.CpHistory;

/**
 * @author llb
 *
 * @Date 2017年12月11日
 */
public interface CpHistoryMapper {
	
	List<CpHistory> findByTypeid(@Param("typeid")int typeid,@Param("start")String start,@Param("end")String end);
	
	
	List<CpHistory> selectByDate(@Param("date")String date,@Param("typeid")int typeid);
	
	
	List<CpHistory> selectHistoryByIdOrderByNoDesc(@Param("typeid")int typeid,@Param("date")int date);
}
