package com.ssyvsse.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ssyvsse.base.entity.Role;
import com.ssyvsse.base.service.support.IBaseService;

/**
 * <p>
 * 角色服务类
 * </p>
 * 
 * @author llb
 *
 * @Date 2018年1月7日 上午10:22:06
 */
public interface IRoleService extends IBaseService<Role, Integer> {

	/**
	 * 添加或修改角色
	 * 
	 * @param role
	 * @param request
	 * @param session
	 */
	void saveOrUpdate(Role role, HttpServletRequest request, HttpSession session);

	/**
	 * 给角色分配资源
	 * 
	 * @param id
	 * @param resourceIds
	 */
	void grant(Integer id, String[] resourceIds);

	/**
	 * 根据关键字查询分页
	 * 
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<Role> findAllByLike(String searchText, PageRequest pageRequest);

}
