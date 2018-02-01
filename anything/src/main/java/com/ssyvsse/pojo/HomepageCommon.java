package com.ssyvsse.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author llb
 *
 * @Date 2018年1月29日 下午6:42:12
 */
@Entity
@Table(name = "homepage_common")
@DynamicInsert
@DynamicUpdate
public class HomepageCommon implements Serializable {

	private static final long serialVersionUID = -3522629335938906927L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 注释
	 */
	private String comment;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 图标
	 */
	private String image;

	/**
	 * 命名
	 */
	private String name;
	/**
	 * 父id
	 */
	private Integer parentId;

	/**
	 * SEO描述
	 */
	private String seodescribe;

	/**
	 * SEO关键字
	 */
	private String seokeywords;
	/**
	 * 分类
	 */
	private Integer sort;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 网址
	 */
	private String url;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getSeodescribe() {
		return seodescribe;
	}

	public void setSeodescribe(String seodescribe) {
		this.seodescribe = seodescribe;
	}

	public String getSeokeywords() {
		return seokeywords;
	}

	public void setSeokeywords(String seokeywords) {
		this.seokeywords = seokeywords;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
