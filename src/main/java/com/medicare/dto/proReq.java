package com.medicare.dto;


public class proReq {
	private String name;
	private int price;
	private String category;
	private int quantity;
	private String seller;
	private String descr;
	private Boolean active=true;
	private String imageURL;
	public proReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public proReq(String name, int price, String category, int quantity, String seller, String descr, Boolean active,
			String imageURL) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
		this.seller = seller;
		this.descr = descr;
		this.active = active;
		this.imageURL = imageURL;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
}
