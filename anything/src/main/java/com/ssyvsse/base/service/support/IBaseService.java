package com.ssyvsse.base.service.support;

import java.io.Serializable;
import java.util.List;

/**
 * @author llb
 *
 * @Date 2018年1月5日
 */
public interface IBaseService<T, ID extends Serializable> {
	
	public abstract T find(ID id);

    public abstract List<T> findAll();

    public abstract List<T> findList(ID[] ids);

    public abstract List<T> findList(Iterable<ID> ids);

    public abstract long count();

    public abstract boolean exists(ID id);

    public abstract void save(T entity);

    public abstract T update(T entity);

    public abstract void delete(ID id);

    public abstract void deleteByIds(@SuppressWarnings("unchecked") ID... ids);

    public abstract void delete(T[] entitys);

    public void delete(Iterable<T> entitys);

    public abstract void delete(T entity);

}
