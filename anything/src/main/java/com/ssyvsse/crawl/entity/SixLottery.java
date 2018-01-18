package com.ssyvsse.crawl.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ssyvsse.base.entity.support.BaseEntity;

/**
 * @author llb
 *
 * @Date 2018年1月18日
 */
@Entity
@Table(name = "sixLottery")
@DynamicInsert
@DynamicUpdate
public class SixLottery extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "no")
	private String no;

	@Column(name = "before_nums")
	private String before_nums;

	@Column(name = "before_specialnums")
	private String before_specialnums;

	@Column(name = "after_nums")
	private String after_nums;

	@Column(name = "after_specialnums")
	private String after_specialnums;

	@Column(name = "typeid")
	private int typeid;

	@Column(name = "money")
	private BigDecimal money;

	@Column(name = "createtime")
	private String createtime;

	@Column(name = "opendate")
	private String opendate;

	@Column(name = "status")
	private String status;

	@Column(name = "img")
	private String img;

	@Column(name = "userid")
	private Integer userid;

	@Column(name = "auditUserid")
	private Integer auditUserid;

	@Column(name = "comment")
	private String comment;

	@Column(name = "shortpy")
	private String shortpy;

	@Column(name = "audittime")
	private String audittime;

	@Column(name = "platform")
	private String platform;

	@Column(name = "typename")
	private String typename;

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		if (audittime.indexOf(".") > 0) {
			int i = audittime.indexOf(".");
			this.audittime = audittime.substring(0, i);
		} else
			this.audittime = audittime;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getBefore_nums() {
		return before_nums;
	}

	public void setBefore_nums(String before_nums) {
		this.before_nums = before_nums == null ? null : before_nums.trim();
	}

	public String getBefore_specialnums() {
		return before_specialnums;
	}

	public void setBefore_specialnums(String before_specialnums) {
		this.before_specialnums = before_specialnums == null ? null : before_specialnums.trim();
	}

	public String getAfter_nums() {
		return after_nums;
	}

	public void setAfter_nums(String after_nums) {
		this.after_nums = after_nums == null ? null : after_nums.trim();
	}

	public String getAfter_specialnums() {
		return after_specialnums;
	}

	public void setAfter_specialnums(String after_specialnums) {
		this.after_specialnums = after_specialnums == null ? null : after_specialnums.trim();
	}

	public String getShortpy() {
		return shortpy;
	}

	public void setShortpy(String shortpy) {
		this.shortpy = shortpy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		// System.out.println("no = " + no);
		this.no = no == null ? null : no.trim();
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {

		if (createtime.indexOf(".") > 0) {
			int i = createtime.indexOf(".");
			this.createtime = createtime.substring(0, i);
		} else
			this.createtime = createtime;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getAuditUserid() {
		return auditUserid;
	}

	public void setAuditUserid(Integer auditUserid) {
		this.auditUserid = auditUserid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}
}
