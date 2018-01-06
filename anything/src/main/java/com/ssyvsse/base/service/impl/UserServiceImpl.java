package com.ssyvsse.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.dao.support.IUserDao;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.util.CryptUtils;
import com.ssyvsse.util.MD5Utils;

/**
 * @author llb
 *
 * @Date 2018年1月5日
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public JsonResult regist(User user, HttpServletRequest request) {
		User entity = getUserDao().findByUserName(user.getUserName());
		if(entity==null) {
			try {
				user.setLoginType(user.getLoginType());
				user.setCreateTime(new Date());
				user.setDeleteStatus(0);
				user.setPassword(CryptUtils.GetMD5Code(user.getPassword()));
				getUserDao().save(user);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonResult.failure("注册失败");
			}
			return JsonResult.success();
		}else {
			return JsonResult.failure("用户名已存在");
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println(CryptUtils.GetMD5Code("123456789"));
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	@Override
	public JsonResult findByUserNameAndPassword(User user,HttpSession session) {
		User entity = userDao.findByUserName(user.getUserName());
		if(entity!=null) {
			if("background".equals(user.getLoginType())) {
				if(entity.getPassword().equals(user.getPassword())) {
					session.setAttribute("user", user);
					return JsonResult.success();
				}else {
					return JsonResult.failure("用户名或密码错误!");
				}
			}else {
				return JsonResult.success();
			}
		}else {
			return JsonResult.failure("用户不存在");
		}
	}

	
	@Override
	public User find(String id) {
		return getUserDao().findById(id);
	}

	@Override
	public List<User> findAll() {
		return getUserDao().findAll();
	}

	@Override
	public List<User> findList(String[] ids) {
		List<User> userList = new ArrayList<User>();
		for (int i = 0, len = ids.length; i < len; i++) {
			User user = find(ids[i]);
			if (user != null) {
				userList.add(user);
			}
		}
		return userList;
	}

	@Override
	public List<User> findList(Iterable<String> ids) {
		List<User> userList = new ArrayList<User>();
		for (String string : ids) {
			User user = find(string);
			if (user != null) {
				userList.add(user);
			}
		}
		return userList;
	}

	@Override
	public long count() {
		return findAll().size();
	}

	@Override
	public boolean exists(String id) {
		return getUserDao().findById(id) != null;
	}

	@Override
	public void save(User entity) {
		if (entity.getId() != null) {

			getUserDao().saveAndFlush(entity);
		} else {
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			entity.setDeleteStatus(0);
			entity.setLoginType(entity.getLoginType());
			if (entity.getPassword() != null) {
				entity.setPassword(MD5Utils.md5(entity.getPassword()));
			} else {
				entity.setPassword(MD5Utils.md5("111111"));
			}
			getUserDao().save(entity);
		}
	}

	@Override
	public User update(User entity) {
		if (entity.getId() != null) {
			return getUserDao().saveAndFlush(entity);
		}
		return null;
	}

	@Override
	public void delete(String id) {
		getUserDao().deleteById(id);
	}

	@Override
	public void deleteByIds(String... ids) {
		for (int i = 0; i < ids.length; i++) {
			delete(ids[i]);
		}
	}

	@Override
	public void delete(User[] entitys) {
		for (User user : entitys) {
			delete(user);
		}
	}

	@Override
	public void delete(Iterable<User> entitys) {
		for (User user : entitys) {
			delete(user);
		}
	}

	@Override
	public void delete(User entity) {
		getUserDao().delete(entity);
	}

	@Override
	public IUserDao getUserDao() {
		return this.userDao;
	}

	
}
