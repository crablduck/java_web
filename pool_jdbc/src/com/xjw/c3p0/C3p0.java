package com.xjw.c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0 {

	private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
	 public static Connection getConnection(){
		 try {
			return comboPooledDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
}
