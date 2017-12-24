package com.dream.test;

import com.dream.entity.Student;

public class Test01 {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Student stu1 = new Student();
		Student stu2 = new Student();
		
		//获取Class对象 --- 属性、方法、构造方法进行操作
		
		//方式一：
		Class<?> class1 = stu1.getClass();
		Class<?> class2 = stu2.getClass();
		
		//方式二：
		Class<?> class3 = Student.class;
		
		//方式三：（强烈推荐！！！）
		Class<?> class4 = Class.forName("com.dream.entity.Student");
		
	}
}
