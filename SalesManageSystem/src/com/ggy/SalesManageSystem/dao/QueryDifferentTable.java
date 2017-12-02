package com.ggy.SalesManageSystem.dao;

public interface QueryDifferentTable {
	/**
	 * 根据表名查询全部表
	 * @param tabname
	 */
	public void queryAll(String tabname);
	
	/**
	 * 根据值查询全部表
	 * @param tabname
	 * @param colname
	 */
	public void queryByColname(String tabname , String value);	

}
