package com.ggy.SalesManageSystem.entity;

import java.util.Date;

/**
 * @author LIN LIANGBIN
 */
public class Goods {
	/**
	 * 条形码
	 */
	private String bar_code;
	/**
	 * 商品名称
	 */
	private String goods_name;
	/**
	 * 计量单位
	 */
	private String measurement_Unit;
	/**
	 * 单价
	 */
	private double unit_price;
	/**
	 * 类型代号
	 */
	private int type_id;

	/**
	 * 数量
	 */
	private double count;
	/**
	 * 最后更新
	 */
	private Date lastModified;

	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getMeasurement_Unit() {
		return measurement_Unit;
	}

	public void setMeasurement_Unit(String measurement_Unit) {
		this.measurement_Unit = measurement_Unit;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return bar_code + "\t" + goods_name + "\t" + measurement_Unit + "\t"
				+ unit_price + "\t" + type_id + "\t" + lastModified;
	}

}
