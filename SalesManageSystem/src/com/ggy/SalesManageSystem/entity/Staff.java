package com.ggy.SalesManageSystem.entity;
/**
 * @author LTW
 *
 */
public class Staff {
	/**
	 * 员工号
	 */
	private int E_NO;
	/**
	 * 姓名
	 */
	private String Name;
	/**
	 * 密码
	 */
	private String Pwd;
	/**
	 * 性别
	 */
	private String Gender;
	/**
	 * 生日
	 */
	private String Birth;
	/**
	 * 地址
	 */
	private String Address;
	/**
	 * 电话
	 */
	private String Phone;
	/**
	 * 身份证号
	 */
	private String ID_card;
	/**
	 * 员工类型
	 */
	private String Em_type;
	/**
	 * 属于商户
	 */
	private String FromMarket;
	
	
	public int getE_NO() {
		return E_NO;
	}
	public void setE_NO(int e_NO) {
		E_NO = e_NO;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getID_card() {
		return ID_card;
	}
	public void setID_card(String iD_card) {
		ID_card = iD_card;
	}
	public String getEm_type() {
		return Em_type;
	}
	public void setEm_type(String em_type) {
		Em_type = em_type;
	}
	public String getFromMarket() {
		return FromMarket;
	}
	public void setFromMarket(String fromMarket) {
		FromMarket = fromMarket;
	}
	
	
	
	@Override
	public String toString() {
		return "Staff [E_NO=" + E_NO + ", Name=" + Name + ", Pwd=" + Pwd
				+ ", Gender=" + Gender + ", Birth=" + Birth + ", Address="
				+ Address + ", Phone=" + Phone + ", ID_card=" + ID_card
				+ ", Em_type=" + Em_type + ", FromMarket=" + FromMarket + "]";
	}
	
	
	


	
	
	
}
