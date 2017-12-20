package com.xjw.pool3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Condition;

import org.junit.Test;

import com.xjw.util.DBUtils;

public class Test1 {

	@Test
	public void getConnection() throws SQLException {

		Connection connection = PoolUtil.getConnection();
		// PoolUtil.close(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
