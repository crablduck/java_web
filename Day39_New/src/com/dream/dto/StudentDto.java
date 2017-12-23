package com.dream.dto;

import com.dream.entity.Student;

//dto目的：展示数据
public class StudentDto {

	private Student stu;
	private String loveStr;
	
	public StudentDto() {
	}
	public StudentDto(Student stu, String loveStr) {
		this.stu = stu;
		this.loveStr = loveStr;
	}
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public String getLoveStr() {
		return loveStr;
	}
	public void setLoveStr(String loveStr) {
		this.loveStr = loveStr;
	}
}
