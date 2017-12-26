package com.ssyvsse.service;

/**
 * @author llb
 *
 * @Date 2017年12月17日 下午9:05:36 
 */
public interface OpenTimeService {
	/**
	 * 检测最新期号是否正确
	 * @param expect
	 * @param typeid
	 * @param newNo
	 * @return
	 */
	boolean checkNewNo(String expect, int typeid,int newNo);
	
	
	
}
