package com.ssyvsse.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author llb
 *
 * @Date 2017年12月8日
 */
public class Cplatest implements Serializable {

	private static final long serialVersionUID = -8634459362746715180L;

	private Integer typeid;

	private String no;

	private String nums;

	private String specialnums;

	private String createtime;

	private String opendate;

	private String img;

	private String status;

	private BigDecimal money;

	private String comment;

	private Integer newno; // 最新一期开奖的期号是当天的第几期
	
	/***
	 * 下一期
	 */
	private Integer nextno;

	public Integer getNewno() {
		return newno;
	}

	public void setNewno(Integer newno) {
		this.newno = newno;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no == null ? null : no.trim();
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums == null ? null : nums.trim();
	}

	public String getSpecialnums() {
		return specialnums;
	}

	public void setSpecialnums(String specialnums) {
		this.specialnums = specialnums == null ? null : specialnums.trim();
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public Integer getNextno() {
		return nextno;
	}

	public void setNextno(Integer nextno) {
		this.nextno = nextno;
	}
}