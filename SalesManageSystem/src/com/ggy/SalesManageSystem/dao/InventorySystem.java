package com.ggy.SalesManageSystem.dao;

import java.util.List;

import com.ggy.SalesManageSystem.entity.Goods;

/**
 * @author LIN LIANGBIN
 * @date 2017年6月22日  上午8:57:10
 */
public interface InventorySystem {
	/**
	 * 查询商品信息
	 * @param Goods_Name
	 * @return
	 */
	public List<Goods> query(String Goods_Name);
	
	/**
	 * 查询全部信息
	 * @return
	 */
	public List<Goods> queryAll();
	
	/**
	 * 修改商品信息
	 * @param g
	 * @return
	 */
	public boolean update(Goods g);
	
	/**
	 * 删除商品信息
	 * @param g
	 * @return
	 */
	public boolean delete(Goods g);
	/**
	 * 增加商品信息
	 * @param g
	 * @return
	 */
	public boolean add(Goods g);
	/**
	 * 根据条形码查询
	 * @param bar_code
	 * @return
	 */
	public Goods queryByBar_code(String bar_code);
	/**
	 * 根据商品名称查询
	 * @param goods_name
	 * @return
	 */
	public List<Goods> queryBYGoods_name(String goods_name);
	
}
