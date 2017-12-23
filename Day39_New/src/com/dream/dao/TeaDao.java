package com.dream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dream.entity.Student;
import com.dream.entity.Teacher;
import com.dream.utils.DBUtils;
import com.dream.utils.MyArrays;

public class TeaDao {

	public int getAllCount() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		int count = 0;
		try {
			connection = DBUtils.getConnection();
			String sql = "select count(1) from teacher";
			prepareStatement = connection.prepareStatement(sql);
			executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				count = executeQuery.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, null);
		}
		
		return count;
	}

	public ArrayList<Teacher> getTeaList(int index, int pageSize) {
	
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		ArrayList<Teacher> list = new ArrayList<>();
		
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from teacher limit ?,?";
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, index);//偏移量
			prepareStatement.setInt(2, pageSize);//获取多少条数据
			
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				
				int id = executeQuery.getInt("id");
				String username = executeQuery.getString("username");
				
				Teacher tea = new Teacher(username);
				
				list.add(tea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, executeQuery);
		}
		return list;
	}

}
