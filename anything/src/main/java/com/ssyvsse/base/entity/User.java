package com.ssyvsse.base.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssyvsse.base.entity.support.BaseEntity;

/**
 * <p>
 * 用户账户表
 * </p>
 * 
 * @author llb
 *
 * @Date 2018年1月7日 上午11:51:35
 */
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", nullable = false)
	private String id;

	/**
	 * 账户名
	 */

	private String userName;

	/**
	 * 昵称
	 */
	private String nickName;


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", nickName=" + nickName + ", password=" + password
				+ ", sex=" + sex + ", birthday=" + birthday + ", telephone=" + telephone + ", email=" + email
				+ ", address=" + address + ", deleteStatus=" + deleteStatus + ", locked=" + locked + ", description="
				+ description + ", createTime=" + createTime + ", updateTime=" + updateTime + ", roles=" + roles
				+ ", code=" + code + ", old=" + old + ", loginType=" + loginType + ", sessionid=" + sessionid + ", age="
				+ age + ", user_ip=" + user_ip + ", last_login_time=" + last_login_time + ", head_portrait="
				+ head_portrait + ", token=" + token + ", score=" + score + ", city=" + city + ", num=" + num
				+ ", wechatId=" + wechatId + ", qid=" + qid + ", sound=" + sound + ", province=" + province + ", alias="
				+ alias + ", secret=" + secret + ", binding=" + binding + ", google_open=" + google_open
				+ ", registerType=" + registerType + ", registUrl=" + registUrl + "]";
	}

	/**
	 * 用户密码
	 */
	@JSONField(serialize = false)
	private String password;

	/**
	 * 性别 0 女 1 男
	 */
	private Integer sex;

	/**
	 * 出生日期
	 */
	@JSONField(format = "yyyy-MM-dd")
	private Date birthday;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 逻辑删除状态 0 未删除 1 删除
	 */
	private Integer deleteStatus;

	/**
	 * 是否锁定
	 * 
	 * 0 未锁定 1 锁定
	 */
	private Integer locked;

	/**
	 * 用户描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private java.util.Set<Role> roles;

	// 验证码
	private String code;
	private String old;
	private String loginType;
	private String sessionid;
	private Integer age;
	// private String cookie;
	private String user_ip;
	@JSONField(format = "yyyy-MM-dd")
	private Date last_login_time;
	private String head_portrait;

	private String token;
	private Integer score;

	private String city;
	private Integer num;

	private Integer wechatId;
	private Integer qid;
	private Integer sound = -1;
	private String province;
	private Integer alias = 2;// PC端注册默认为2，APP注册默认为1
	private String secret;
	private String binding="未绑定";
	private String google_open="开启";

	public String getGoogle_open()
	{
		return google_open;
	}

	public void setGoogle_open(String google_open)
	{
		this.google_open = google_open;
	}

	public String getSecret()
	{
		return secret;
	}

	public void setSecret(String secret)
	{
		this.secret = secret;
	}

	public String getBinding()
	{
		return binding;
	}

	public void setBinding(String binding)
	{
		this.binding = binding;
	}
	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	/**
	 * 注册类型
	 */
	private String registerType;

	/**
	 * 注册页的链接
	 */
	private String registUrl;

	public String getRegistUrl() {
		return registUrl;
	}

	public void setRegistUrl(String registUrl) {
		this.registUrl = registUrl;
	}

	public Integer getAlias() {
		return alias;
	}

	public void setAlias(Integer alias) {
		this.alias = alias;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getSound() {
		return sound;
	}

	public void setSound(Integer sound) {
		this.sound = sound;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getHead_portrait() {
		return head_portrait;
	}

	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(java.util.Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getWechatId() {
		return wechatId;
	}

	public void setWechatId(Integer wechatId) {
		this.wechatId = wechatId;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

}
