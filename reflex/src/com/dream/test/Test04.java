package com.dream.test;

import java.lang.reflect.Constructor;

import com.dream.entity.Student;

public class Test04 {

	public static void main(String[] args) throws Exception {
		
		//利用反射技术创建对象
		
		Class<?> class1 = Class.forName("com.dream.entity.Student");
		
		//使用无参构造创建对象
//		Constructor<?> constructor1 = class1.getConstructor();
//		Student stu = (Student) constructor1.newInstance();
		
		//使用有参构造创建对象
		Constructor<?> constructor2 = class1.getConstructor(String.class,int.class,String.class,String.class);
		Student stu = (Student) constructor2.newInstance("张少键",88,"不明","火星");
		
		System.out.println(stu);
	}
}
