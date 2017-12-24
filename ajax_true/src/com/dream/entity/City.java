package com.dream.entity;

public class City {
	
	private String id;//shenzhen
	private String cityName;//深圳
	
	public City() {
	}

	public City(String id, String cityName) {
		this.id = id;
		this.cityName = cityName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + "]";
	}

}
