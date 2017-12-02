package com.ssyvsse.wechat.config;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.Gson;
import com.ssyvsse.base.entity.support.BaseEntity;

/**
 * 封装 access_token
 */
public class AccessToken extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String access_token; // 正确获取到 access_token 时有值
	private Integer expires_in; // 正确获取到 access_token 时有值
	private Integer errcode; // 出错时有值
	private String errmsg; // 出错时有值

	private Long expiredTime; // 正确获取到 access_token 时有值，存放过期时间
	private String json;

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

	public String getJson() {
		return json;
	}

	public boolean isAvailable() {
		if (expiredTime == null)
			return false;
		if (errcode != null)
			return false;
		if (expiredTime < System.currentTimeMillis())
			return false;
		return access_token != null;
	}

	private Integer getInt(Map<String, Object> temp, String key) {
		Number number = (Number) temp.get(key);
		return number == null ? null : number.intValue();
	}

	public String getAccessToken() {
		return access_token;
	}

	public Long getExpiredTime() {
		return expiredTime;
	}

	public Integer getErrorCode() {
		return errcode;
	}

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

	public Integer getExpiresIn() {
		return expires_in;
	}

	public void setExpiresIn(Integer expires_in) {
		this.expires_in = expires_in;
	}

}
