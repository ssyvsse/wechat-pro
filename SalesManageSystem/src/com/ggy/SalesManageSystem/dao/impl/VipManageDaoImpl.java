package com.ggy.SalesManageSystem.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.dao.VipManageDao;
import com.ggy.SalesManageSystem.entity.vip_manage;

/**
 * @author ltw
 *
 */
public class VipManageDaoImpl implements VipManageDao {
	private DBUtils util=null;

	@Override
	public boolean add(vip_manage vip_manage) {
		util = new DBUtils();
		String sql = "insert into vip_manage values(?,?,?,?)";
		Object[] params = {vip_manage.getVIP_ID(),vip_manage.getName(),vip_manage.getGender(),vip_manage.getTel()};
		return util.update(sql, params)>=1;
		
	}

	@Override
	public boolean update(vip_manage vip_manage) {
		util = new DBUtils();
		String sql = "update vip_manage set Name=?,Gender=?,Tel=? where VIP_ID=?";
		Object[] params={vip_manage.getVIP_ID(),vip_manage.getName(),vip_manage.getTel(),vip_manage.getGender(),vip_manage.getVIP_ID()};
		return util.update(sql, params)>=1;
	}

	@Override
	public boolean delete(int VIP_ID) {
		util = new DBUtils();
		String sql = "delete from vip_manage where VIP_ID=?";
		return util.update(sql, VIP_ID)>=1;
	}

	@Override
	public vip_manage queryById(int VIP_ID) {
		util=new DBUtils();
		return vip_manageMapper(util.query("select * from vip_manage where VIP_ID=?",VIP_ID)).get(0);
	}

	@Override
	public List<vip_manage> queryAll() {
		util=new DBUtils();
		return vip_manageMapper(util.query("select * from vip_manage"));	
	}
	
	/**
	 * 数据库字段和成员变量的映射
	 * @param rs
	 * @return
	 */
	private List<vip_manage> vip_manageMapper(ResultSet rs){
		List<vip_manage> _list=new ArrayList<>();
		try {
			while(rs.next()){
				vip_manage vm=new vip_manage();
				vm.setVIP_ID(rs.getInt("VIP_ID"));
				vm.setName(rs.getString("Name"));
				vm.setTel(rs.getString("Tel"));
				//vm.setVIP_Gender(rs.getString("VIP_Gender").charAt(0));
				vm.setGender(rs.getString("Gender"));
				/*vm.setVIP_Amount(rs.getDouble("VIP_Amount"));
				vm.setIntegral(rs.getInt("VIP_Integral"));
				vm.setLevel(rs.getInt("VIP_Level"));*/
				_list.add(vm);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			util.close();
		}
		return _list;
	}
	

}
