package com.dream.entity;

public class Student {

	private String username;
	private String password;
	private String sex;
	private String city;
	private String loves;
	private String photoPath;
	
	public Student() {
	}

	public Student(String username,String password, String sex, String city, String loves, String photoPath) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.city = city;
		this.loves = loves;
		this.photoPath = photoPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLoves() {
		return loves;
	}

	public void setLoves(String loves) {
		this.loves = loves;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student [username=" + username + ", password=" + password + ", sex=" + sex + ", city=" + city
				+ ", loves=" + loves + ", photoPath=" + photoPath + "]";
	}
	
	
}
