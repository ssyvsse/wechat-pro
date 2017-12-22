package com.ssyvsse.wechat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author llb
 *
 * @Date 2017年12月10日 下午3:57:46
 */
//@Entity
//@Table(name = "uploadImg")
public class PicMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8486395076473624740L;
	private Integer id;
	private String mediaId;
	private String picUrl;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createTime;
	private String createBy;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Override
	public String toString() {
		return "PicMsg [id=" + id + ", mediaId=" + mediaId + ", picUrl=" + picUrl + ", createTime=" + createTime
				+ ", createBy=" + createBy + "]";
	}

}
