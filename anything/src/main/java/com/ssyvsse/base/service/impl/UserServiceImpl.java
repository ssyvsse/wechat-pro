package com.ssyvsse.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.dao.IUserDao;
import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.Role;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IRoleService;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.base.service.support.impl.BaseServiceImpl;
import com.ssyvsse.util.CryptUtils;
import com.ssyvsse.util.MD5Utils;

/**
 * @author llb
 *
 * @Date 2018年1月5日
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, String> implements IUserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleService roleService;

	@Override
	public User find(String id) {
		return userDao.findById(id);
	}

	@Override
	public IBaseDao<User, String> getBaseDao() {
		return this.userDao;
	}

	@Override
	public User findByNiceName(String nickName) {
		return userDao.findByNickName(nickName);
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public JsonResult regist(User user, HttpServletRequest request) {
		User entity = userDao.findByUserName(user.getUserName());
		if (entity == null) {
			try {
				user.setLoginType(user.getLoginType());
				user.setCreateTime(new Date());
				user.setDeleteStatus(0);
				user.setPassword(MD5Utils.md5(user.getPassword()));
				user.setLocked(0);
				user.setUpdateTime(new Date());
				getBaseDao().save(user);
				logger.info("注册成功");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("注册失败");
				return JsonResult.failure("注册失败");
			}
			return JsonResult.success();
		} else {
			return JsonResult.failure("用户名已存在");
		}
	}

	@Override
	public void delete(String id) {
		User user = find(id);
		Assert.state(!"admin".equals(user.getUserName()), "超级管理员用户不能删除");
		super.delete(id);
	}

	@Override
	public void saveOrUpdate(User user, HttpServletRequest request, HttpSession session) {
		if (user.getId() != null && !"".equals(user.getId())) {
			User dbUser = find(user.getId());
			dbUser.setNickName(user.getNickName());
			if (user.getPassword() != null) {
				dbUser.setPassword(MD5Utils.md5(user.getPassword()));
			} else {
				dbUser.setPassword(dbUser.getPassword());
			}
			dbUser.setSex(user.getSex());
			dbUser.setBirthday(user.getBirthday());
			dbUser.setTelephone(user.getTelephone());
			dbUser.setEmail(user.getEmail());
			dbUser.setAddress(user.getAddress());
			dbUser.setLocked(user.getLocked());
			dbUser.setDescription(user.getDescription());
			dbUser.setUpdateTime(new Date());
			update(dbUser);
		} else {
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setDeleteStatus(0);
			user.setLoginType("background");
			if (user.getPassword() != null) {
				user.setPassword(MD5Utils.md5(user.getPassword()));
			} else {
				user.setPassword(MD5Utils.md5("111111"));
			}
			save(user);
		}
	}

	@Override
	public void grant(String id, String[] roleIds, HttpServletRequest request) {
		User user = find(id);
		Assert.notNull(user, "用户不存在");
		Assert.state(!"admin".equals(user.getUserName()), "超级管理员用户不能管理角色");
		Role role;
		Set<Role> roles = new HashSet<Role>();
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				Integer rid = Integer.parseInt(roleIds[i]);
				role = roleService.find(rid);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		update(user);
	}

	@Override
	public IUserDao getUserDao() {
		return this.userDao;
	}

	@Override
	public User findByPassword(String id) {
		return userDao.findByPassword(id);
	}

	@Override
	public int updatePwd(String id, String password) {
		String newPwd = MD5Utils.md5(password);
		return userDao.updatePwd(newPwd, id);
	}

}
