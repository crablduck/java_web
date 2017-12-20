package com.xjw.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;
import com.xjw.util.DBUtils;

public class Transaction {

	@Test
	public void testTransaction() throws SQLException{
		
		Connection connection = DBUtils.getConnection();
		String sql = "update blank set money=money-100 where id=1";
		String sql1 = "update blank set money=money+100 where id=2";

		connection.setAutoCommit(false);
		
		Statement createStatement = connection.createStatement();
		createStatement.executeUpdate(sql);
		
		int a = 10/0;
		
		Statement createStatement2 = connection.createStatement();
		createStatement2.executeUpdate(sql1);
		
		connection.commit();
		
		DBUtils.close(connection, createStatement2, null);
		DBUtils.close(connection, createStatement, null);
		
	}
	@Test
	public void testTransaction1() throws SQLException{
		
		Connection connection = DBUtils.getConnection();
		String sql = "update blank set money=money-100 where id=1";
		String sql1 = "update blank set money=money+100 where id=2";
		
		
		
		Statement createStatement = connection.createStatement();
		createStatement.executeUpdate(sql);
		
		int a = 10/0;
		
		Statement createStatement2 = connection.createStatement();
		createStatement2.executeUpdate(sql1);
		
		DBUtils.close(connection, createStatement2, null);
		DBUtils.close(connection, createStatement, null);
		
	}
}
