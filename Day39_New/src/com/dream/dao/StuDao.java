package com.dream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dream.dto.StudentDto;
import com.dream.entity.Student;
import com.dream.utils.DBUtils;
import com.dream.utils.MyArrays;

public class StuDao {

	public ArrayList<Student> getStuList() {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		ArrayList<Student> list = new ArrayList<>();
		
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from student where flag=1";
			prepareStatement = connection.prepareStatement(sql);
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				
				int id = executeQuery.getInt("id");
				String username = executeQuery.getString("username");
				String sex = executeQuery.getString("sex");
				String city = executeQuery.getString("city");
				String lovestr = executeQuery.getString("lovestr");
				
				Student stu = new Student();
				stu.setId(id);
				stu.setUsername(username);
				stu.setSex(sex);
				stu.setCity(city);
				stu.setLoves(MyArrays.str4Arrays(lovestr));
				
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, executeQuery);
		}
		return list;
	}
	
	public ArrayList<Student> getStuList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		ArrayList<Student> list = new ArrayList<>();
		
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from student where flag=1 limit ?,?";
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, index);//偏移量
			prepareStatement.setInt(2, pageSize);//获取多少条数据
			
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				
				int id = executeQuery.getInt("id");
				String username = executeQuery.getString("username");
				String sex = executeQuery.getString("sex");
				String city = executeQuery.getString("city");
				String lovestr = executeQuery.getString("lovestr");
				
				Student stu = new Student();
				stu.setId(id);
				stu.setUsername(username);
				stu.setSex(sex);
				stu.setCity(city);
				stu.setLoves(MyArrays.str4Arrays(lovestr));
				
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, executeQuery);
		}
		return list;
	}

	public int isRegister(String username) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		int num = 0;
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from student where username=? and flag=1";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, username);
			executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				num = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, executeQuery);
		}
		return num;
	}

	public int add(StudentDto stuDto) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int num  = 0;
		try {
			connection = DBUtils.getConnection();
			String sql = "insert into student(username,password,sex,city,lovestr,flag) values(?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, stuDto.getStu().getUsername());
			prepareStatement.setString(2, stuDto.getStu().getPassword());
			prepareStatement.setString(3, stuDto.getStu().getSex());
			prepareStatement.setString(4, stuDto.getStu().getCity());
			prepareStatement.setString(5, stuDto.getLoveStr());
			prepareStatement.setInt(6, stuDto.getStu().getFlag());
			
			num  = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, null);
		}
		return num;
	}

	public int delete(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int num  = 0;
		try {
			connection = DBUtils.getConnection();
			String sql = "update student set flag=0 where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1,id);
			num  = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, null);
		}
		return num;
	}

	public Student getStubyId(int id) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		Student stu = null;
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from student where id=? and flag=1";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1,id);
			executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				
				String username = executeQuery.getString("username");
				String sex = executeQuery.getString("sex");
				String city = executeQuery.getString("city");
				String lovestr = executeQuery.getString("lovestr");
				
				stu = new Student();
				stu.setId(id);
				stu.setUsername(username);
				stu.setSex(sex);
				stu.setCity(city);
				stu.setLoves(MyArrays.str4Arrays(lovestr));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, null);
		}
		return stu;
	}

	public int update(StudentDto stuDto) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int num  = 0;
		try {
			connection = DBUtils.getConnection();
			String sql = "update student set sex=? , city=? , lovestr=? where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, stuDto.getStu().getSex());
			prepareStatement.setString(2, stuDto.getStu().getCity());
			prepareStatement.setString(3, stuDto.getLoveStr());
			prepareStatement.setInt(4, stuDto.getStu().getId());
			
			num  = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, null);
		}
		return num;
	}

	public int getAllCount() {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		int count = 0;
		try {
			connection = DBUtils.getConnection();
			String sql = "select count(1) from student where flag=1";
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

	public Student getStuByUsernamePassword(String username, String password) {

		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from student where username=? and password=? and flag=1";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, username);
			prepareStatement.setString(2,password);
			
			executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				
				int id = executeQuery.getInt("id");
				username = executeQuery.getString("username");
				password = executeQuery.getString("password");
				String sex = executeQuery.getString("sex");
				String city = executeQuery.getString("city");
				String lovestr = executeQuery.getString("lovestr");
				
				Student stu = new Student();
				stu.setId(id);
				stu.setUsername(username);
				stu.setSex(sex);
				stu.setCity(city);
				stu.setLoves(MyArrays.str4Arrays(lovestr));
				
				return stu;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, prepareStatement, null);
		}
		
		return null;
	}

}
