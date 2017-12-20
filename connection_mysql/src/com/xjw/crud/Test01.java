package com.xjw.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xjw.util.DBUtils;

public class Test01 {

	public static void main(String[] args){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/1707javaee", "root", "root");
			statement = connection.createStatement();
			String sql = "select * from student";
			resultSet = statement.executeQuery(sql);

			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String classId = resultSet.getString("c_id");

				System.out.println(id + " -- " + username + " -- " + classId);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

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

	private static void select() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/1707javaee", "root", "root");

		Statement statement = connection.createStatement();
		String sql = "select * from student";
		ResultSet resultSet = statement.executeQuery(sql);

		while(resultSet.next()){

			int id = resultSet.getInt("id");
			String username = resultSet.getString("username");
			String classId = resultSet.getString("c_id");

			System.out.println(id + " -- " + username + " -- " + classId);

		}

		resultSet.close();
		statement.close();
		connection.close();
	}

	private static void delete() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/1707javaee", "root", "root");

		Statement statement = connection.createStatement();
		String sql = "delete from student where id=5";
		int result = statement.executeUpdate(sql);//�����Ӱ����1��

		System.out.println(result);

	}

	private static void update() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");


		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		String sql = "update student set username='zf' where id=5";
		int result = statement.executeUpdate(sql);

		System.out.println(result);
	}

	private static void add() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/1707javaee", "root", "root");

		Statement statement = connection.createStatement();
		String sql = "insert into student(username,c_id) values('xj',2)";
		int result = statement.executeUpdate(sql);

		System.out.println(result);
	}

}
