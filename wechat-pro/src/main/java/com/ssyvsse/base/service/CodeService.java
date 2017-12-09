package com.ssyvsse.base.service;

import javax.servlet.http.HttpSession;

/**
 * @author llb
 *
 * @Date 2017年11月30日 下午11:28:50
 */
public interface CodeService {

	/**
	 * 验证图形验证码
	 * 
	 * @param imgcode
	 * @param session
	 * @return
	 */
	public boolean checkCode(String imgcode, HttpSession session);

}
