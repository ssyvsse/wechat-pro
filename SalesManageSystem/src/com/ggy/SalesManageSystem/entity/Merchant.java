package com.ggy.SalesManageSystem.entity;

import javax.swing.JOptionPane;

public class Merchant {
	/**
	 * 商户账号
	 */
	private String e_no;
	/**
	 * 商户名称
	 */
	private String name;
	/**
	 * 商户密码
	 */
	private String password;
	/**
	 * 商户邮箱
	 */
	private String email;
	/**
	 * 商户联系方式
	 */
	private String phone;
	/**
	 * 商户地址
	 */
	private String address;

	public String getE_no() {
		return e_no;
	}

	public void setE_no(String e_no) {
		this.e_no = e_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(!"".equals(name)){
			if(name.length()<=24)
				this.name = name;
			else{
				this.name = "";	
				JOptionPane.showMessageDialog(null, "用户名不超过24位！");
			}
		}else{
			JOptionPane.showMessageDialog(null, "请输入用户名！");
		}		
	}	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		int len = password.length();
		if(len>=6&&len<=16)
			this.password = password;
		else if(len<6){
			this.password= password;
			JOptionPane.showMessageDialog(null, "密码不少于6位！");
		}else{
			this.password="";
			JOptionPane.showMessageDialog(null, "密码不超过16位！");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"))
			this.email = email;
		else{
			this.email="";
			JOptionPane.showMessageDialog(null, "邮箱格式错误！");
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		int len = phone.length();
		if(len>11){
			this.phone="";
			JOptionPane.showMessageDialog(null, "联系方式不超过11位！");			
		}
		for (int i = 0; i < len; i++) {
			if(!Character.isDigit(phone.charAt(i)))
				this.phone = "";
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
