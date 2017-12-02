package com.ssyvsse.crawl.entity;

import java.io.Serializable;

/**
 * @author llb
 *
 * @Date 2017年12月2日 上午9:54:40 
 */
public class Novel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4261427359983587121L;
	private Integer id;
	private String chapter;
	private String author;
	private String content;
	private String createTime;
	private String createBy;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "Novel [id=" + id + ", chapter=" + chapter + ", author=" + author + ", content=" + content
				+ ", createTime=" + createTime + ", createBy=" + createBy + "]";
	}

}
