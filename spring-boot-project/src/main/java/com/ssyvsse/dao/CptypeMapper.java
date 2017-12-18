package com.ssyvsse.dao;

import org.apache.ibatis.annotations.Param;

import com.ssyvsse.pojo.Cptype;

/**
 * @author llb
 *
 * @Date 2017年12月11日
 */
public interface CptypeMapper {
	
	Cptype selectById(@Param("id")int id);
	
	Cptype selectByPrimaryKey(Integer id);
	
}
