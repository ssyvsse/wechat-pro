package com.ssyvsse.base.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ssyvsse.base.dao.IResourceDao;
import com.ssyvsse.base.dao.IRoleDao;
import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.Resource;
import com.ssyvsse.base.entity.Role;
import com.ssyvsse.base.service.IRoleService;
import com.ssyvsse.base.service.support.impl.BaseServiceImpl;

/**
 * @author llb
 *
 * @Date 2018年1月7日 上午11:13:29
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IResourceDao resourceDao;
	
	@Override
	public IBaseDao<Role, Integer> getBaseDao() {
		return this.roleDao;
	}

	@Override
	public void saveOrUpdate(Role role, HttpServletRequest request, HttpSession session) {
		if (role.getId() != null) {
			Role dbRole = find(role.getId());
			dbRole.setUpdateTime(new Date());
			dbRole.setName(role.getName());
			dbRole.setDescription(role.getDescription());
			dbRole.setUpdateTime(new Date());
			dbRole.setStatus(role.getStatus());
			update(dbRole);
		} else {
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			save(role);
		}
	}

	@Override
	@CacheEvict(value = "resourceCache", key = "'tree_' + #id")
	public void grant(Integer id, String[] resourceIds) {
		Role role = find(id);
		Assert.notNull(role, "角色不存在");
		Assert.state(!"administrator".equals(role.getRoleKey()), "超级管理员角色不能进行资源分配");
		Resource resource;
		Set<Resource> resources = new HashSet<Resource>();
		if (resourceIds != null) {
			for (int i = 0; i < resourceIds.length; i++) {
				if (StringUtils.isBlank(resourceIds[i]) || "0".equals(resourceIds[i])) {
					continue;
				}
				Integer rid = Integer.parseInt(resourceIds[i]);
				resource = resourceDao.findOne(rid);// 00000
				resources.add(resource);
			}
		}
		role.setResources(resources);
		update(role);
	}

	@Override
	public Page<Role> findAllByLike(String searchText, PageRequest pageRequest) {
		if (StringUtils.isBlank(searchText)) {
			searchText = "";
		}
		return roleDao.findAllByNameContainingOrDescriptionContaining(searchText, searchText, pageRequest);
	}

}
