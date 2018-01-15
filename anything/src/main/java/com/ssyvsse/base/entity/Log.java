package com.ssyvsse.base.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssyvsse.base.entity.support.BaseEntity;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午4:55:24
 */
@Entity
@Table(name="system_log")
@DynamicInsert
@DynamicUpdate
public class Log extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5182508353087908027L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String operator;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date operationTime;

	private String operationalContext;

	private String ip;

	@Override
	public String toString() {
		return "Log [id=" + id + ", operator=" + operator + ", operationTime=" + operationTime + ", operationalContext="
				+ operationalContext + ", ip=" + ip + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationalContext() {
		return operationalContext;
	}

	public void setOperationalContext(String operationalContext) {
		this.operationalContext = operationalContext;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
