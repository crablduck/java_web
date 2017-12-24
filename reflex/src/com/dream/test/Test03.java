package com.dream.test;

import java.lang.reflect.Method;

import com.dream.entity.Student;

public class Test03 {

	public static void main(String[] args) throws Exception {
		
		//利用反射技术调用stu对象中的setCity方法
		
		Student stu = new Student();
		
		Class<?> class1 = Class.forName("com.dream.entity.Student");
		//Method[] methods = class1.getMethods();//获得普通的方法，包括父类
		//Method[] declaredMethods = class1.getDeclaredMethods();//获得所有的方法，不包括父类
		
		Method method1 = class1.getDeclaredMethod("setCity",String.class);
		method1.setAccessible(true);//私有化才需要打开权限，不管是属性还是方法
		method1.invoke(stu, "上海");
		
		Method method2 = class1.getMethod("setAge", int.class);
		method2.invoke(stu, 88);
		
		System.out.println(stu.getAge());
		
		
		
		
		
	}
}
