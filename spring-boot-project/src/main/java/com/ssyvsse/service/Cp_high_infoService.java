package com.ssyvsse.service;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.ssyvsse.pojo.Cp_high_info;

public interface Cp_high_infoService {
	
	PageInfo<Cp_high_info>selectAll(int page,int num);
	
	int update (Cp_high_info cp_high_info,HttpServletRequest request);
	
	Cp_high_info find(Integer id);
	
	int deleteCp(Integer id,HttpServletRequest request);
	
	PageInfo<Cp_high_info>findAllByLike(Integer id,int page,int num);
	
	int add (Cp_high_info cp_high_info,HttpServletRequest request);
	
	int lockOrUnlock(Cp_high_info cp_high_info,HttpServletRequest request);
}
