package com.xjw.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {


	private static Connection connection;
	private static String url;
	private static String username;
	private static String password;
	
	//静态代码块
	static{
		
		try {
			//加载驱动包
			Class.forName("com.mysql.jdbc.Driver");
			
			//查找到配置文件中的可用信息
			Properties p = new Properties();
			//查找src文件夹下的指定文件
			InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			p.load(in);
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static ThreadLocal<Connection> local = new ThreadLocal<>();
	
	public static Connection getConnection() throws SQLException{
		Connection connection = local.get();
		if(connection == null){
			connection = DriverManager.getConnection(url,username, password);
			local.set(connection);
		}
		return connection;
	}
	
	public static void close(Connection connection,Statement statement,ResultSet resultSet){
		//正确关闭资源 (创建顺序：connection，statement，resultSet) (销毁顺序：反过来)
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
	
	public static void startTransaction(){
		Connection connection;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollback(){
		Connection connection = local.get();
		try {
			connection.rollback();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		local.set(null);
	}
	
	public static void commit(){
		Connection connection = local.get();
		try {
			connection.commit();
			connection.close();
			local.set(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
