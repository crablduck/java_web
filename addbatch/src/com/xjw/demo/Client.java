package com.xjw.demo;

import java.sql.SQLException;
import java.util.Scanner;

import com.xjw.dao.Dao;
import com.xjw.util.DBUtils;

public class Client {
	
	public static void main(String[] args){
		
		Dao dao = new Dao();
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		int classId = scan.nextInt();
		
		DBUtils.startTransaction();
		
		try {
			dao.addStudent(username,classId);
			dao.updateClass(classId);
		} catch (SQLException e) {
			DBUtils.rollback();
			System.err.println("���ʧ��");
		}finally {
			DBUtils.commit();
		}
		
	}
}
