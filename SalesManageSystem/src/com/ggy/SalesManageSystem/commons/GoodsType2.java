package com.ggy.SalesManageSystem.commons;
/**
 * @author Administrator
 * @Date 2017年6月23日 上午7:37:38
 */
public class GoodsType2 {
	
	private int type_id;
	private String type;
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public GoodsType2(int type_id){
		this.type_id = type_id;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
