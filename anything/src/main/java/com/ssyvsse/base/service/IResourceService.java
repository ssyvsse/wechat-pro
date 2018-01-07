package com.ssyvsse.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ssyvsse.base.entity.Resource;
import com.ssyvsse.base.entity.ZtreeView;
import com.ssyvsse.base.service.support.IBaseService;

/**
 * @author llb
 *
 * @Date 2018年1月7日 上午11:52:42 
 */
public interface IResourceService extends IBaseService<Resource,Integer>{
	
	/**
	 * 获取角色的权限树
	 * @param roleId
	 * @return
	 */
	List<ZtreeView> tree(int roleId);

	/**
	 * 修改或新增资源
	 * @param resource
	 * @param request
	 * @param session
	 */
	void saveOrUpdate(Resource resource,HttpServletRequest request,HttpSession session);
	
	/**
	 * 关键字分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<Resource> findAllByLike(String searchText,PageRequest pageRequest);
}
