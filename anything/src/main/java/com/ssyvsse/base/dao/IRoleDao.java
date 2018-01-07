package com.ssyvsse.base.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.Role;

/**
 * @author llb
 *
 * @Date 2018年1月7日 上午11:09:26
 */
@Repository
public interface IRoleDao extends IBaseDao<Role, Integer> {

	Page<Role> findAllByNameContainingOrDescriptionContaining(String searchText1,String searchText2,Pageable pageable);
}
