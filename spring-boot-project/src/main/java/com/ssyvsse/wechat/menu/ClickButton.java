package com.ssyvsse.wechat.menu;



/**
 * click类型按钮（子按钮）
 * 
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class ClickButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1799202804226915903L;
	
	/**
	 * 按钮的key
	 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
