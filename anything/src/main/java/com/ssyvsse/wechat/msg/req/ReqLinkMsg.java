package com.ssyvsse.wechat.msg.req;

/**
 * 链接消息
 *
 */
public class ReqLinkMsg extends ReqBaseMsg {
	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 消息描述
	 */
	private String description;
	/**
	 * 消息链接
	 */
	private String url;

	public ReqLinkMsg(String toUserName, String fromUserName, Integer createTime, String msgId, String title,
			String description, String url) {
		super(toUserName, fromUserName, createTime, "link", msgId);
		this.title = title;
		this.description = description;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}