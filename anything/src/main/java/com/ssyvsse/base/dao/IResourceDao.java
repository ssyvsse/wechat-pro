package com.ssyvsse.base.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.Resource;

/**
 * @author llb
 *
 * @Date 2018年1月7日 上午11:46:52 
 */
@Repository
public interface IResourceDao extends IBaseDao<Resource,Integer>{
	
	@Modifying
	@Query(nativeQuery = true,value="DELETE FROM TB_ROLE_RESOURCE WHERE RESOURCE_ID = :id")
	void deleteGrant(@Param("id")Integer id);
	
	Page<Resource> findAllByNameContaining(String searchText,Pageable pageable);
	
	List<Resource> findAllByOrderByParentAscIdAscSortAsc();
}
