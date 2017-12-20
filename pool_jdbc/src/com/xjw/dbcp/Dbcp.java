package com.xjw.dbcp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.xjw.util.DBUtils;

public class Dbcp {

	private static DataSource dataSource;

	static {

		try {
			// 加载驱动包
			Class.forName("com.mysql.jdbc.Driver");

			// 查找到配置文件中的可用信息
			Properties p = new Properties();
			// 查找src文件夹下的指定文件
			InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			p.load(in);
			
			dataSource = BasicDataSourceFactory.createDataSource(p);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
