package com.ssyvsse.base.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.dao.IUserDao;
import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.CodeService;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.base.service.support.impl.BaseServiceImpl;
import com.ssyvsse.utils.CPSMS;

/**
 * @author llb
 *
 * @Date 2017年11月29日 下午11:06:58
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private CodeService codeService;

	@Override
	public User findByUserName(String username) {
		return null;
	}

	@Override
	public void saveOrUpdate(User user, HttpServletRequest request, HttpSession session) {
		if (user == null) {
			return;
		}
		if (user.getId() != null) {
		}

	}

	@Override
	public JsonResult getRetrieveCode(HttpServletRequest request, String phone, String imgcode, HttpSession session) {
		String phoneFilter = phone.substring(0, 3);
		User user = userDao.findByTelephone(phone);
		if (user != null) {
			return JsonResult.failure("该手机号码已注册。");
		}
		if (codeService.checkCode(imgcode, session)) {
			if (!phoneFilter.equals("170") && !phoneFilter.equals("171")) {
				JsonResult result = CPSMS.sendRegister(request, phone);
				if (result.getCode() == 0) {
					request.getSession().setAttribute("RegisterPhone", phone);
					request.getSession().setAttribute("RegisterCode", request.getSession().getAttribute("LoginCode"));
					request.getSession().setAttribute("RegisterCodeTime", new Date().getTime());
					return JsonResult.success(result.getMessage(), result.getData());
				} else {
					return JsonResult.failure(result.getMessage());
				}
			}
		} else {
			return JsonResult.failure("170,171不发送手机验证码");
		}
		return JsonResult.failure("图形验证码错误。");

	}

	@Override
	public IBaseDao<User, Integer> getBaseDao() {
		return null;
	}

}
