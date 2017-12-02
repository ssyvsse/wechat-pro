package com.ssyvsse.base.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssyvsse.base.dao.IUserDao;
import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.CodeService;
import com.ssyvsse.base.service.support.impl.BaseServiceImpl;

/**
 * @author llb
 *
 * @Date 2017年11月30日 下午11:29:21
 */
@Service
public class CodeServiceImpl extends BaseServiceImpl<User, Integer> implements CodeService {

	@Autowired
	private IUserDao userDao;

	@Override
	public boolean checkCode(String imgcode, HttpSession session) {
		String code = (String) session.getAttribute("loginCode");
		if (imgcode != null && code != null) {
			if (code.toLowerCase().equals(imgcode.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public IBaseDao<User, Integer> getBaseDao() {
		return this.userDao;
	}

}
