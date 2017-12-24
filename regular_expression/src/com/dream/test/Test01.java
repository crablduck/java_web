package com.dream.test;

public class Test01 {
	
	//判断功能

	public static void main(String[] args) {
		/**
		 * 正则表达式：具有规律的字符串
		 * 学习目的：1.看得懂，2.会修改
		 */
		
		boolean checkEmail = checkEmail("1445504980@qq.com.cn");
		System.out.println(checkEmail);
		
	}
	public static boolean checkEmail(String email){
		
//		String regex = "\\w+@\\w+\\.\\w+";
		String regex = "\\w+@\\w+(\\.\\w+)+";
		boolean matches = email.matches(regex);
		return matches;
	}
	
	
	public static boolean checkPhone(String phone){
		
//		String regex = "1[0-9]{10}";
//		String regex = "1\\d{10}";
//		String regex = "1[3|8]\\d{9}";
		String regex = "((13[0-9])|(14[5|7])|(15[0-3]|[5-9])|(18[0,5-9]))\\d{8}";
		boolean matches = phone.matches(regex);
		return matches;
	}
	
//	public static boolean checkPhone(String phone){
//		
//		if(phone.length() != 11){
//			return false;
//		}
//		char[] charArray = phone.toCharArray();
//		for (char c : charArray) {
//			if(c<48 || c>57){
//				return false;
//			}
//		}
//		return true;
//	}
	
}
