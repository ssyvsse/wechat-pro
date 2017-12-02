package com.ggy.SalesManageSystem.dao;

import java.util.List;

import com.ggy.SalesManageSystem.entity.Staff;


/**
 * @author LTW
 *
 */
public interface StaffManageDao {
	
	/**
	 * 添加员工
	 * @param staff
	 * @return
	 */
	public boolean add(Staff staff);
	/**
	 * 更新员工信息
	 * @param staff
	 * @return
	 */
	public boolean update(Staff staff);
	/**
	 * 删除员工信息
	 * @param E_NO
	 * @return
	 */
	public boolean delete(int E_NO);
	/**
	 * 根据员工号查找员工
	 * @param E_NO
	 * @return
	 */
	public Staff queryById(int E_NO);
	/**
	 * 查询全部员工
	 * @return
	 */
	public List<Staff> queryAll();

}