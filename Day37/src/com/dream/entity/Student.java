package com.dream.entity;

import java.util.Arrays;

public class Student {
	
	//属性
	private String username;
	private String password;
	private String sex;
	private String city;
	private String[] loves;

	//构造方法
	public Student() {}

	public Student(String username, String password, String sex, String city, String[] loves) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.city = city;
		this.loves = loves;
	}

	//方法(get/set)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String[] getLoves() {
		return loves;
	}

	public void setLoves(String[] loves) {
		this.loves = loves;
	}
	
	@Override
	public String toString() {
		return "Student [username=" + username + ", password=" + password + ", sex=" + sex + ", city=" + city
				+ ", loves=" + Arrays.toString(loves) + "]";
	}
}
