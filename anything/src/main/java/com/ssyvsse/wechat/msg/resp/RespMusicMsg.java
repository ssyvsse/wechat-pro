package com.ssyvsse.wechat.msg.resp;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午10:45:05
 */
public class RespMusicMsg extends RespBaseMsg {

	/**
	 * 音乐标题
	 */
	private String title;

	/**
	 * 音乐描述
	 */
	private String description;

	/**
	 * 音乐链接
	 */
	private String musicURL;

	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 */
	private String HQMusicURL;

	/**
	 * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	 */
	private String thumbMediaId;

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

	public String getMusicURL() {
		return musicURL;
	}

	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}

	public String getHQMusicURL() {
		return HQMusicURL;
	}

	public void setHQMusicURL(String hQMusicURL) {
		HQMusicURL = hQMusicURL;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Override
	protected void subXml(StringBuilder sb) {
		if (thumbMediaId == null || "".equals(thumbMediaId)) {
			throw new NullPointerException("thumbMediaId is null.");
		}
		sb.append("<Music>");
		if (title != null && !"".equals(title)) {
			sb.append("<Title>< ![CDATA[").append(title).append("] ]></Title>");
		}
		if (description != null && !"".equals(description)) {
			sb.append("<Description>< ![CDATA[").append(description).append("] ]></Description>");
		}
		if (musicURL != null && !"".equals(musicURL)) {
			sb.append("<MusicUrl>< ![CDATA[").append(musicURL).append("] ]></MusicUrl>");
		}
		if (HQMusicURL != null && !"".equals(HQMusicURL)) {
			sb.append("<HQMusicUrl>< ![CDATA[").append(HQMusicURL).append("] ]></HQMusicUrl>");
		}
		sb.append("<ThumbMediaId>< ![CDATA[").append(thumbMediaId).append("] ]></ThumbMediaId></Music>");
	}

}
