package com.ssyvsse.pojo;

/**
 * @author llb
 *
 * @Date 2017年12月11日
 */
public class OpenTime {
	private Integer typeid;
	private Integer no;
	private String opentime;
	private String period;

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "OpenTime [typeid=" + typeid + ", no=" + no + ", opentime=" + opentime + ", period=" + period + "]";
	}

	
}
