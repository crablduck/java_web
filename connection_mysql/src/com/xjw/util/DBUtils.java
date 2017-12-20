package com.xjw.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//���ݿ⹤�߰�
public class DBUtils {

	private static Connection connection;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Properties p = new Properties();
			InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			p.load(in);
			url = p.getProperty("jdbc.url");
			username = p.getProperty("jdbc.username");
			password = p.getProperty("jdbc.password");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username, password);
	}
	
	public static void close(Connection connection,Statement statement,ResultSet resultSet){
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
