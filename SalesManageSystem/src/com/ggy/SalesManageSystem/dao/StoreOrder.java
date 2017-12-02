package com.ggy.SalesManageSystem.dao;

import java.util.List;

import com.ggy.SalesManageSystem.entity.Store;

public interface StoreOrder {
	/**
	 * 增加订货
	 * @param store
	 * @return
	 */
	public boolean add(Store store);
	/**
	 * 查询全部订单
	 * @return
	 */
	public List<Store> queryAll();
	/**
	 * 根据订单号查询订单
	 * @param obj
	 * @return
	 */
	public List<Store> queryById(Object  obj);
}
