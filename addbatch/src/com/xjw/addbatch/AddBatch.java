package com.xjw.addbatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.xjw.util.DBUtils;

public class AddBatch {

	@Test
	public void addBatch() throws SQLException{
		
		Connection connection = DBUtils.getConnection();
		
		Statement statement = connection.createStatement();
		
		String sql = "insert into tb_user(name, password) value('xiaoming', '123456')";
		String sql1 = "update tb_user set name='xiaohong', password='321321' where id=6";
		
		statement.addBatch(sql);
		statement.addBatch(sql1);
		
		int[] executeBatch = statement.executeBatch();
		for (int i : executeBatch) {
			System.out.println(i);
		}
	}
	
	@Test
	public void addBatch1() throws SQLException{
		
		Connection connection = DBUtils.getConnection();
		
		String sql = "insert into tb_user(name, password) value(?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);

		for (int i = 0; i < 5; i++) {
			statement.setString(1, "name_"+i);
			statement.setString(2, "password_"+i);
			statement.addBatch();
		}
		
		int[] executeBatch = statement.executeBatch();
		for (int i : executeBatch) {
			System.out.println(i);
		}
	}
	
	@Test
	public void addBatch2() throws SQLException{
		
		Connection connection = DBUtils.getConnection();
		
		String sql = "insert into tb_user(name, password) value(?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		for (int i = 0; i < 100; i++) {
			statement.setString(1, "name_"+i);
			statement.setString(2, "password_"+i);
			statement.addBatch();
			
			if(i%5 == 0){
				statement.executeBatch();
				statement.clearBatch();
			}
		}
		
		statement.executeBatch();
	}
	
	
	
	@Test
	public void testConnection() throws SQLException{
		Connection connection = DBUtils.getConnection();
		DBUtils.close(connection, null, null);
	}
}
