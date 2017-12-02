package com.ggy.SalesManageSystem.entity;

import javax.swing.JOptionPane;

/**
 * @author LIN LIANGBIN
 */
public class User {
	
	/**
	 * 工号
	 */
	private String e_no;
	
	/**
	 * 密码
	 */
	private String password;
	
	public String getE_no() {
		return e_no;
	}
	public void setE_no(String e_no) {
		if(e_no.length()>0 && e_no.length()<=6){
			boolean bl = true;
			for (int i = 0; i < e_no.length(); i++) {
				if(!Character.isDigit(e_no.charAt(i)))
					bl = false;
			}
			if(bl)
				this.e_no = e_no;
			else{
				this.e_no="";
				JOptionPane.showMessageDialog(null, "请输入6位数工号，如100001");		
			}
		}else
			JOptionPane.showMessageDialog(null, "请输入6位数工号，如100001");		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password.length()>0 && password.length()<=16)
			this.password = password;
		else{
			this.password="";
			JOptionPane.showMessageDialog(null, "请输入不超过16位的密码");
		}
	}
	
	
}
