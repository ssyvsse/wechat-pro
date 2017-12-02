package com.ggy.SalesManageSystem.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.dao.StaffManageDao;
import com.ggy.SalesManageSystem.entity.Staff;


/**
 * @author ltw
 *
 */
public class StaffManageDaoImpl implements StaffManageDao{
	private DBUtils util = null;

	@Override
	public boolean add(Staff staff) {
		util = new DBUtils();
		String sql = "insert into Staff(name,pwd,gender,birth,address,phone,id_card,em_type,frommarket) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {staff.getName(),staff.getPwd(),staff.getGender(),staff.getBirth(),staff.getAddress(),staff.getPhone(),staff.getID_card(),staff.getEm_type(),staff.getFromMarket()};
		return util.update(sql, params)>=1;
	}

	@Override
	public boolean update(Staff staff) {
		util = new DBUtils();
		String sql = "update Staff set Name=?,Pwd=?,Gender=?,Birth=?,Address=?,Phone=?,ID_card=?;Author_No=?,FromMarket=? where E_NO=?";
		Object[] params = {staff.getE_NO(),staff.getName(),staff.getPwd(),staff.getGender(),staff.getBirth(),staff.getAddress(),staff.getPhone(),staff.getID_card(),staff.getEm_type(),staff.getFromMarket(),staff.getE_NO()};
		return util.update(sql, params)>=1;
	}

	@Override
	public boolean delete(int E_NO) {
		util = new DBUtils();String sql = "delete from Staff where E_NO=?";
		return util.update(sql, E_NO)>=1;
	}

	@Override
	public Staff queryById(int E_NO) {
		util=new DBUtils();
		return staffMapper(util.query("select * from Staff where E_NO=?",E_NO)).get(0);
	}

	/**
	 * 数据库字段和成员变量的映射
	 * @param rs
	 * @return
	 */
	private List<Staff> staffMapper(ResultSet rs) {
		List<Staff> _list=new ArrayList<>();
		try {
			while(rs.next()){
				Staff sm=new Staff();
				sm.setE_NO(rs.getInt("E_NO"));
				sm.setName(rs.getString("Name"));
				sm.setPwd(rs.getString("Pwd"));
				sm.setGender(rs.getString("Gender"));
				sm.setBirth(rs.getString("Birth"));
				sm.setAddress(rs.getString("Address"));
				sm.setPhone(rs.getString("Phone"));
				sm.setID_card(rs.getString("ID_card"));
				sm.setEm_type(rs.getString("Em_type"));
				sm.setFromMarket(rs.getString("FromMarket"));
				_list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			util.close();
		}
		return _list;
	}

	@Override
	public List<Staff> queryAll() {
		util=new DBUtils();
		return staffMapper(util.query("select * from Staff"));	
	}
	
}
