package com.ggy.SalesManageSystem.entity;
/**
 * @author ltw
 *
 */
public class vip_manage {
	/**
	 * 会员号
	 */
	private int VIP_ID;
	/**
	 * 姓名
	 */
	private String Name;
	/**
	 * 电话
	 */
	private String Tel;
	/**
	 * 性别
	 */
	private String Gender;
	
	
	public int getVIP_ID() {
		return VIP_ID;
	}
	public void setVIP_ID(int vIP_ID) {
		VIP_ID = vIP_ID;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	@Override
	public String toString() {
		return "vip_manage [VIP_ID=" + VIP_ID + ", Name=" + Name
				+ ", Tel=" + Tel + ", Gender=" + Gender + "]";
	}
	
	

	
}
