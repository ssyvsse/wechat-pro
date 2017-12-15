package com.ssyvsse.pojo;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 彩票类别
 * @author pxy
 *
 */

public class Cp_high_info implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2098841091826877456L;

	private Integer id;
	
	private String show_name;
	
	private String short_name;
	
	private String frequency;
	
	
	private Integer cp_high_typeid;
	
	private Integer times;
	
	private Integer stop_delay;
	
	private BigDecimal max_bonus;
	
	private Integer sort;
	
	private Integer status;
	
	private String description;
	
	private String name;
	
	private String platform;
	private Integer notype;
	

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getNotype() {
		return notype;
	}

	public void setNotype(Integer notype) {
		this.notype = notype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShow_name() {
		return show_name;
	}

	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getCp_high_typeid() {
		return cp_high_typeid;
	}

	public void setCp_high_typeid(Integer cp_high_typeid) {
		this.cp_high_typeid = cp_high_typeid;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getStop_delay() {
		return stop_delay;
	}

	public void setStop_delay(Integer stop_delay) {
		this.stop_delay = stop_delay;
	}

	public BigDecimal getMax_bonus() {
		return max_bonus;
	}

	public void setMax_bonus(BigDecimal max_bonus) {
		this.max_bonus = max_bonus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

	

	
}
