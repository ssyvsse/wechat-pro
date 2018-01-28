package com.ssyvsse.wechat.pojo;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.google.gson.Gson;
import com.ssyvsse.wechat.config.ReturnCode;

/**
 * @author llb
 *
 * @Date 2018年1月14日 上午9:05:01
 */
@Entity
@Table(name = "tb_access_token")
@DynamicInsert
@DynamicUpdate
public class AccessToken implements Serializable {

	private static final long serialVersionUID = -3191728468894026075L;

	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 正确获取到access_token时有值
	 */
	private String access_token;
	/**
	 * 过期时间 单位：秒
	 */
	@Transient
	private Integer expires_in;
	/**
	 * 错误代码
	 */
	@Transient
	private Integer errcode;
	/**
	 * 错误信息
	 */
	@Transient
	private String errmsg;
	/**
	 * 过期时间
	 */
	@Column(name = "expired_time", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Long expiredTime;
	/**
	 * json 信息
	 */
	@Transient
	private String json;

	private String type;

	private boolean available;

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Column(name = "type", length = 30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AccessToken() {
	}

	@SuppressWarnings("unchecked")
	public AccessToken(String jsonStr) {
		this.json = jsonStr;
		try {
			Map<String, Object> temp = new Gson().fromJson(jsonStr, Map.class);
			access_token = (String) temp.get("access_token");
			expires_in = getInt(temp, "expires_in");
			errcode = getInt(temp, "errcode");
			errmsg = (String) temp.get("errmsg");

			if (expires_in != null)
				expiredTime = System.currentTimeMillis() + ((expires_in - 5) * 1000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transient
	public String getJson() {
		return json;
	}

	@Column(name = "available", length = 1, nullable = false, columnDefinition = "int(1)")
	public boolean isAvailable() {
		if (expiredTime == null)
			return false;
		if (errcode != null)
			return false;
		if (expiredTime < System.currentTimeMillis())
			return false;
		available = access_token != null;
		return available;
	}

	private Integer getInt(Map<String, Object> temp, String key) {
		Number number = (Number) temp.get(key);
		return number == null ? null : number.intValue();
	}

	public Long getExpiredTime() {
		if (this.expiredTime != null) {
			return expiredTime;
		} else {
			if (this.expires_in != null) {
				return System.currentTimeMillis() + (this.expires_in - 5) * 1000;
			} else {
				return null;
			}
		}
	}

	@Transient
	public Integer getErrorCode() {
		return errcode;
	}

	@Transient
	public String getErrorMsg() {
		if (errcode != null) {
			String result = ReturnCode.get(errcode);
			if (result != null)
				return result;
		}
		return errmsg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@Transient
	public Integer getExpiresIn() {
		return expires_in;
	}

	public void setExpiresIn(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public void setExpiredTime(Long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
