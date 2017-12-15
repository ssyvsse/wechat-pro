package com.ssyvsse.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssyvsse.dao.Cp_high_infoMapper;
import com.ssyvsse.pojo.Cp_high_info;
import com.ssyvsse.service.Cp_high_infoService;

@Service
public class Cp_high_infoService2Impl implements Cp_high_infoService {
	
	@Autowired
	private Cp_high_infoMapper cp_high_infoMapper;
	
	
	
	@Override
	public PageInfo<Cp_high_info> selectAll(int page,int num) {
		PageHelper.startPage(page, num);
		List<Cp_high_info> list = cp_high_infoMapper.selectAll();
		PageInfo<Cp_high_info> pageInfo = new PageInfo<Cp_high_info>(list);
		return pageInfo;
	}



	@Override
	public int update(Cp_high_info cp_high_info,HttpServletRequest request) {
		return cp_high_infoMapper.update(cp_high_info);
		
	}



	@Override
	public Cp_high_info find(Integer id) {
		return cp_high_infoMapper.find(id);
	}
	
	@Override
	public int deleteCp(Integer id,HttpServletRequest request){
		return cp_high_infoMapper.deleteCp(id);
	}



	@Override
	public PageInfo<Cp_high_info> findAllByLike(Integer id, int page, int num) {
		PageHelper.startPage(page, num);
		List<Cp_high_info> list = cp_high_infoMapper.findAllByLike(id);
		PageInfo<Cp_high_info> pageInfo = new PageInfo<Cp_high_info>(list);
		return pageInfo;
	}



	@Override
	public int add(Cp_high_info cp_high_info,HttpServletRequest request) {
		return cp_high_infoMapper.add(cp_high_info);
	}



	@Override
	public int lockOrUnlock(Cp_high_info cp_high_info,HttpServletRequest request) {
		int i=cp_high_info.getStatus();
		if(i==0){
			cp_high_info.setStatus(-1);
		}else{
			cp_high_info.setStatus(0);
		}
		return cp_high_infoMapper.update(cp_high_info);
	}

}
