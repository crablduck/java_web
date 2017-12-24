package com.dream.entity;

public class Student {

	public String name;
	public int age;
	private String sex;
	private String city;
	public Student() {
	}
	public Student(String name, int age, String sex, String city) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	private void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex + ", city=" + city + "]";
	}
	
}
