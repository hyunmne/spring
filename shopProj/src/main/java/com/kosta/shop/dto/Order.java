package com.kosta.shop.dto;

public class Order {
	private Integer num;
	private String userid;
	private String gCode;
	private String gName;
	private Integer gPrice;
	private String gSize;
	private String gColor;
	private Integer gAmount;
	private String gImage;
	private String orderName;
	private String post;
	private String addr1;
	private String addr2;
	private String phone;
	private String payMethod;
	private String orderDay;
	
	@Override
	public String toString() {
		return "OrderInfo [num=" + num + ", userid=" + userid + ", gCode=" + gCode + ", gName=" + gName + ", gPrice="
				+ gPrice + ", gSize=" + gSize + ", gColor=" + gColor + ", gAmount=" + gAmount + ", gImage=" + gImage
				+ ", orderName=" + orderName + ", post=" + post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", phone="
				+ phone + ", payMethod=" + payMethod + ", orderDay=" + orderDay + "]";
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getgCode() {
		return gCode;
	}
	public void setgCode(String gCode) {
		this.gCode = gCode;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public Integer getgPrice() {
		return gPrice;
	}
	public void setgPrice(Integer gPrice) {
		this.gPrice = gPrice;
	}
	public String getgSize() {
		return gSize;
	}
	public void setgSize(String gSize) {
		this.gSize = gSize;
	}
	public String getgColor() {
		return gColor;
	}
	public void setgColor(String gColor) {
		this.gColor = gColor;
	}
	public Integer getgAmount() {
		return gAmount;
	}
	public void setgAmount(Integer gAmount) {
		this.gAmount = gAmount;
	}
	public String getgImage() {
		return gImage;
	}
	public void setgImage(String gImage) {
		this.gImage = gImage;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(String orderDay) {
		this.orderDay = orderDay;
	}
	
	
}
