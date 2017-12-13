package com.ssyvsse.pojo;

/**
 * @author llb
 *
 * @Date 2017年12月11日
 */
public class Cptype {
	private Integer id;
	private String typename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "Cptype [id=" + id + ", typename=" + typename + "]";
	}

}
