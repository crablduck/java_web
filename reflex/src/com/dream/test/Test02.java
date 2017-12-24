package com.dream.test;

import java.lang.reflect.Field;

import com.dream.entity.Student;

public class Test02 {

	public static void main(String[] args) throws Exception {
		
		//利用反射技术修改stu对象中的city --- 属性
		Student stu = new Student();
		
		Class<?> class1 = Class.forName("com.dream.entity.Student");
		//Field[] fields = class1.getFields();//获得普通的属性列表
		//Field[] declaredFields = class1.getDeclaredFields();//获得所有的属性列表
		Field field = class1.getDeclaredField("city");//获得此属性对象
		field.setAccessible(true);//打开更改的权限
		field.set(stu, "北京");//给stu里的city属性设置值
		
		System.out.println(stu.getCity());
	}
}
