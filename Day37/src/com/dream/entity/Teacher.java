package com.dream.entity;

public class Teacher {

	private String username;
	private String password;
	private String sex;
	private String city;
	
	public Teacher() {}//用很多第三方框架，框架中用到了许多反射技术，框架底层就需要实体类的无参构造，如果没有，就会莫名其妙的报错

	public Teacher(String username, String password, String sex, String city) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.city = city;
	}

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

	@Override
	public String toString() {
		return "Teacher [username=" + username + ", password=" + password + ", sex=" + sex + ", city=" + city + "]";
	}

}
