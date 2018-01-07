package com.ssyvsse.base.service.support.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.support.BaseEntity;
import com.ssyvsse.base.service.support.IBaseService;

/**
 * @author llb
 *
 * @Date 2018年1月7日 上午11:14:26 
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity,ID extends Serializable> implements IBaseService<T,ID>{

	public abstract IBaseDao<T, ID> getBaseDao();
	
	@Override
	public T find(ID id) {
		return getBaseDao().findOne(id);
	}

	@Override
	public List<T> findAll() {
		return getBaseDao().findAll();
	}

	@Override
	public List<T> findList(ID[] ids) {
		List<ID> idList = Arrays.asList(ids);
		return getBaseDao().findAll(idList);
	}

	@Override
	public List<T> findList(Iterable<ID> ids) {
		return getBaseDao().findAll(ids);
	}

	@Override
	public long count() {
		return getBaseDao().count();
	}

	@Override
	public boolean exists(ID id) {
		return getBaseDao().exists(id);
	}

	@Override
	public void save(T entity) {
		getBaseDao().save(entity);
	}

	@Override
	public T update(T entity) {
		return getBaseDao().save(entity);
	}

	@Override
	public void delete(ID id) {
		getBaseDao().delete(id);
	}

	@Override
	public void deleteByIds(@SuppressWarnings("unchecked") ID... ids) {
		if(ids!=null){
			for (int i = 0; i < ids.length; i++) {
				ID id = ids[i];
				this.delete(id);
			}
		}
	}

	@Override
	public void delete(T[] entitys) {
		List<T> tList = Arrays.asList(entitys);
		getBaseDao().delete(tList);
	}

	@Override
	public void delete(Iterable<T> entitys) {
		getBaseDao().delete(entitys);
	}

	@Override
	public void delete(T entity) {
		getBaseDao().delete(entity);
	}
	
}
