package com.ssyvsse.wechat.menu;

/**
 * view类型按钮
 * 
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class ViewButton extends Button{

	/**
	 * redirect_url 回调地址
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
