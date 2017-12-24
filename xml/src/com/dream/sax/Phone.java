package com.dream.sax;

public class Phone {

	private String type;
	private String price;
	private String store;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Phone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phone(String type, String price, String store) {
		super();
		this.type = type;
		this.price = price;
		this.store = store;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Phone [type=" + type + ", price=" + price + ", store=" + store + ", id=" + id + "]";
	}

}
