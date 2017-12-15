package com.ssyvsse.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssyvsse.pojo.Cp_high_info;

public interface Cp_high_infoMapper {
    
	List<Cp_high_info> selectAll();
	int update(Cp_high_info cp_high_info);
	Cp_high_info find(@Param("id")Integer id);
	int deleteCp(@Param("id") Integer id);
	List<Cp_high_info> findAllByLike(@Param("id")Integer id);
	int add (Cp_high_info cp_high_info);
	
}