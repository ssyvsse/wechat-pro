package com.ssyvsse.wechat.menu;
/**
 * 按钮的基类
 */
public class Button {
	private String name;
	private String type; 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}