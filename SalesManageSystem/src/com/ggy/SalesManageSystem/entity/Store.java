package com.ggy.SalesManageSystem.entity;

public class Store {
    /**
     * 商品名称
     */
    private String Goods_Name;
    /**
     * 进货数量
     */
    private int Quantity_Purchased;
    /**
     * 进货价格
     */
    private double Price;
    /**
     * 商品类型
     */
    private String list;
    /**
     * 订单时间
     */
    private String Duration;
    
	public String getGoods_Name() {
		return Goods_Name;
	}
	public void setGoods_Name(String goods_Name) {
		Goods_Name = goods_Name;
	}
	public int getQuantity_Purchased() {
		return Quantity_Purchased;
	}
	public void setQuantity_Purchased(int quantity_Purchased) {
		Quantity_Purchased = quantity_Purchased;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	@Override
	public String toString() {
		return "Store [Goods_Name=" + Goods_Name + ", Quantity_Purchased="
				+ Quantity_Purchased + ", Price=" + Price + ", list=" + list
				+ ", Duration=" + Duration + "]";
	}
    
   
    
}
