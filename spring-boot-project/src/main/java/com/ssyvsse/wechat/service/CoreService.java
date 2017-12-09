package com.ssyvsse.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
public interface CoreService {
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response);
	
}
