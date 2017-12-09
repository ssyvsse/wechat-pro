package com.ssyvsse.wechat.menu;
/**
 * 按钮的基类
 * 
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class Button {

	/**
	 * 按钮名称
	 */
	private String name;
	
	/**
	 * 按钮类型
	 */
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
