package com.ggy.SalesManageSystem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.dao.StoreOrder;
import com.ggy.SalesManageSystem.entity.Store;

public class StoreOrderImpl implements StoreOrder{
     private DBUtils db=null;
	@Override
	public boolean add(Store store) {
		db=new DBUtils();
		String sql="insert into order1(Goods_Name,Quantity_Purchased,Price,list,Duration)values(?,?,?,?,?)";
		Object[] params={store.getGoods_Name(),store.getQuantity_Purchased(),store.getPrice(),store.getList(),store.getDuration()};
		return db.update(sql, params)>=1;
	}
	
	@Override
	public List<Store> queryAll() {
		db=new DBUtils();
		
		return userMapper(db.query("select * from order1"));
	}	

	private List<Store> userMapper(ResultSet rs){
		List<Store> list =new ArrayList<Store>();
		try {
			while(rs.next()){
			Store store=new Store();
			store.setDuration(rs.getString("Duration"));
			store.setGoods_Name(rs.getString("Goods_Name"));
			store.setPrice(rs.getDouble("Price"));
			store.setList(rs.getString("list"));
			store.setQuantity_Purchased(rs.getInt("Quantity_Purchased"));
			list.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return list;
	}
	
	@Override
	public List<Store> queryById(Object obj) {
		return null;
	}

}
