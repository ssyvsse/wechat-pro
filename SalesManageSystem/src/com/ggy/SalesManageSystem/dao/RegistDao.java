package com.ggy.SalesManageSystem.dao;

import com.ggy.SalesManageSystem.entity.Merchant;

public interface RegistDao {
	/**
	 * 商户注册
	 * @param merchant
	 * @return
	 */
	public boolean regist(Merchant merchant);
}
