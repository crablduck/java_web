package com.xjw.pool1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import com.xjw.util.DBUtils;

public class Pool1 {

	//创建Connection的容器， 因为里面经常要进行插入删除connection 所以用linklist比较好
	private static LinkedList<Connection> list = new LinkedList<Connection>();
	 
	//初始化connection_pool
	static{
		for (int i = 0; i < 20; i++) {
			try {
				Connection connection = DBUtils.getConnection();
				list.add(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//getConnection
	public static Connection getConnection(){
		if(!list.isEmpty()){
			return list.removeFirst();
		}
		return null;
	}
	
	//release pool
	public static void release(Connection connection ){
		list.add(connection);
	}
}
