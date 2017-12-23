package com.dream.db;

import java.util.ArrayList;

import com.dream.entity.Student;
import com.dream.entity.Teacher;

//数据中心 --- 数据库
public class DBCenter {

	//学生容器 -- 表
	public static ArrayList<Student> stulist = new ArrayList<>();

	//教师容器 -- 表
	public static ArrayList<Teacher> tealist = new ArrayList<>();

	//初始化3条数据
	static{
		
		//学生数据
		stulist.add(new Student("xiaohong", "123123", "woman", "beijing", new String[]{"shopping"}));
		stulist.add(new Student("xiaoming", "123123", "man", "shanghai", new String[]{"football"}));
		stulist.add(new Student("xiaoqiang", "123123", "man", "shenzhen", new String[]{"football","basketball"}));
	
		//老师数据
		tealist.add(new Teacher("hhy", "111","man","chengdu"));
		tealist.add(new Teacher("czt", "222","man","shenzhen"));
		tealist.add(new Teacher("jdr", "333","man","beijing"));
	}
}
