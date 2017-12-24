package com.dream.test;

import java.util.Arrays;

public class Test02 {

	public static void main(String[] args) {
		
		//分割功能
		
		String path = "C:\\Users\\apple\\Desktop\\何翰宇Java基础5周课件\\H5资源";
		String[] split = path.split(":?\\\\");
		
		
		String str = "aa  bb      cc   dd";
		String[] split2 = str.split("\\s+");
		
		for (String string : split2) {
			System.out.println(string);
		}
	}
	
	
	
}
