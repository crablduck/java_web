package com.xjw.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.xjw.util.DBUtils;


public class Dao {

	public void addStudent(String username,int classId) throws SQLException{
		
		Connection connection = null;
		Statement statement = null;
		try {
			connection =DBUtils.getConnection();
			statement = connection.createStatement();
			String insertSQL = "insert into student(username,c_id) values('"+username+"',"+classId+")";
			statement.executeUpdate(insertSQL);
			
		}finally {
			DBUtils.close(connection, statement, null);
		}
		
		
	}
	
	public void updateClass(int classId) throws SQLException{
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DBUtils.getConnection();
			statement = connection.createStatement();
			String updateSQL = "update class set sum=sum+1 where id = " + classId;
			statement.executeUpdate(updateSQL);
			
		}finally {
			DBUtils.close(connection, statement, null);
		}
		
	}
	
}









