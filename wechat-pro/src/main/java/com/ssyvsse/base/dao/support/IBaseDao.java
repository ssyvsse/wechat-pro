package com.ssyvsse.base.dao.support;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.ssyvsse.base.entity.support.BaseEntity;

/**
 * @author llb
 *
 * @Date 2017年11月29日 下午10:51:10
 */
@NoRepositoryBean
public interface IBaseDao<T extends BaseEntity, ID extends Serializable>
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
