package com.xjw.pool2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Condition;

import org.junit.Test;

import com.xjw.util.DBUtils;

public class Test1 {

	public static void main(String[] args) {
		Connection connection = PoolUtil.getConnection();
		if (connection != null) {
			System.out.println(1);
		}
		PoolUtil.close(connection);
		
	}
	
	@Test
	public void getConnection() throws SQLException{
		
		Connection connection = DBUtils.getConnection();
		System.out.println(connection!=null?1:0);
	}
}
