package com.ssyvsse.wechat.msg.resp.entity;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午11:06:27
 */
public class Article {

	/**
	 * 图文消息标题
	 */
	private String title;

	/**
	 * 图文消息描述
	 */
	private String description;

	/**
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 */
	private String picURL;

	/**
	 * 点击图文消息跳转链接
	 */
	private String url;

	public Article(String title, String description, String picURL, String url) {
		this.title = title;
		this.description = description;
		this.picURL = picURL;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
