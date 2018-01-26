package com.ssyvsse.socket.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author llb
 *
 * @Date 2018年1月25日
 */
@Entity
@Table(name = "tb_message")
@DynamicInsert
@DynamicUpdate
public class Message {

	@Column(name = "id", length = 11)
	private Integer id;

	private String fromWhere;

	private String fromName;

	private String toWhere;

	private String toName;

	private String text;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Date date;

	@Column(name = "fromWhere", length = 50)
	public String getFromWhere() {
		return fromWhere;
	}

	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}

	@Column(name = "fromName", length = 50)
	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	@Column(name = "toWhere", length = 50)
	public String getToWhere() {
		return toWhere;
	}

	public void setToWhere(String toWhere) {
		this.toWhere = toWhere;
	}

	@Column(name = "toName", length = 50)
	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	@Column(name = "text", length = 1000)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "date", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
