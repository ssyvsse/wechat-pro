package com.ssyvsse.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssyvsse.base.dao.IResourceDao;
import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.Resource;
import com.ssyvsse.base.entity.Role;
import com.ssyvsse.base.entity.ZtreeView;
import com.ssyvsse.base.service.IResourceService;
import com.ssyvsse.base.service.IRoleService;
import com.ssyvsse.base.service.support.impl.BaseServiceImpl;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午12:00:37
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;

	@Autowired
	private IRoleService roleService;

	@Override
	public IBaseDao<Resource, Integer> getBaseDao() {
		return this.resourceDao;
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'tree_' + #roleId")
	public List<ZtreeView> tree(int roleId) {
		List<ZtreeView> resultTreeNodes = new ArrayList<ZtreeView>();
		Role role = roleService.find(roleId);
		Set<Resource> roleResources = role.getResources();
		resultTreeNodes.add(new ZtreeView(0L, null, "系统菜单", true));
		ZtreeView node;
		List<Resource> all = resourceDao.findAllByOrderByParentAscIdAscSortAsc();
		for (Resource resource : all) {
			node = new ZtreeView();
			node.setId(Long.valueOf(resource.getId()));
			if (resource.getParent() == null) {
				node.setpId(0L);
			} else {
				node.setpId(Long.valueOf(resource.getParent().getId()));
			}
			node.setName(resource.getName());
			if (roleResources != null && roleResources.contains(resource)) {
				node.setChecked(true);
			}
			resultTreeNodes.add(node);
		}
		return resultTreeNodes;
	}

	@Override
	public void saveOrUpdate(Resource resource, HttpServletRequest request, HttpSession session) {
		if(resource.getId()!=null){
			Resource dbResource = find(resource.getId());
			dbResource.setUpdateTime(new Date());
			dbResource.setName(resource.getName());
			dbResource.setSourceKey(resource.getSourceKey());
			dbResource.setType(resource.getType());
			dbResource.setSourceUrl(resource.getSourceUrl());
			dbResource.setLevel(resource.getLevel());
			dbResource.setSort(resource.getSort());
			dbResource.setIsHide(resource.getIsHide());
			dbResource.setIcon(resource.getIcon());
			dbResource.setDescription(resource.getDescription());
			dbResource.setUpdateTime(new Date());
			dbResource.setParent(resource.getParent());
			update(dbResource);
		}else{
			resource.setCreateTime(new Date());
			resource.setUpdateTime(new Date());
			save(resource);
		}
	}

	@Override
	public Page<Resource> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return resourceDao.findAllByNameContaining(searchText, pageRequest);
	}

	@Override
	public void delete(Integer id) {
		resourceDao.deleteGrant(id);
		super.delete(id);
	}
	
	
}
