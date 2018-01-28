package com.ssyvsse.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午8:43:52
 */
public interface CoreService {

	/**
	 * 核心业务类
	 * 
	 * @param request
	 * @param response
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response);

}
