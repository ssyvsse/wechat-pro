package com.ssyvsse.wechat.entity;

/**
 * @author llb
 *
 * @Date 2017年12月1日 下午10:25:52
 */
public class WXUserInfo {
	private Integer id;
	private String openId;
	private String nickname;
	private int sex;
	private String province;
	private String city;
	private String headImgUrl;
	private String privilege;
	private String unionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	@Override
	public String toString() {
		return "WXUserInfo [id=" + id + ", openId=" + openId + ", nickname=" + nickname + ", sex=" + sex + ", province="
				+ province + ", city=" + city + ", headImgUrl=" + headImgUrl + ", privilege=" + privilege + ", unionId="
				+ unionId + "]";
	}

}
