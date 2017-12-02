package com.ggy.SalesManageSystem.dao;

import java.util.List;

import com.ggy.SalesManageSystem.entity.vip_manage;

/**
 * @author LTW
 *
 */
public interface VipManageDao {
	/**
	 * 添加会员
	 * @param vip_manage
	 * @return
	 */
	public boolean add(vip_manage vip_manage);
	/**
	 * 修改会员信息
	 * @param vip_manage
	 * @return
	 */
	public boolean update(vip_manage vip_manage);
	/**
	 * 删除会员信息
	 * @param VIP_ID
	 * @return
	 */
	public boolean delete(int VIP_ID);
	/**
	 * 根据会员号查询会员信息
	 * @param VIP_ID
	 * @return
	 */
	public vip_manage queryById(int VIP_ID);
	/**
	 * 查询全部会员信息
	 * @return
	 */
	public List<vip_manage> queryAll();

}
