package com.xjw.pool1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Condition;

import org.junit.Test;

import com.xjw.util.DBUtils;

public class Test1 {

	public static void main(String[] args) {
		Connection connection = Pool1.getConnection();
		if (connection != null) {
			System.out.println(1);
		}
		
		Pool1.release(connection);
	}
	
	@Test
	public void getConnection() throws SQLException{
		
		Connection connection = DBUtils.getConnection();
		System.out.println(connection!=null?1:0);
	}
}
