package com.ssyvsse.base.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * ztree树
 * 
 * @author llb
 *
 * @Date 2018年1月7日 上午11:53:55
 */
public class ZtreeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 78903095838454953L;

	private Long id;

	private Long pId;

	private String name;

	private boolean open;

	private boolean checked = false;

	public ZtreeView() {

	}

	public ZtreeView(Long id, Long pId, String name, boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "ZtreeView [id=" + id + ", pId=" + pId + ", name=" + name + ", open=" + open + ", checked=" + checked
				+ "]";
	}

}
