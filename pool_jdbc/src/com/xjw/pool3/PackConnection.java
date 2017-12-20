package com.xjw.pool3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class PackConnection extends ImplConnection{
	
	Connection connection;
	LinkedList<Connection> pool;
	
	//代理模式， 用这个进行代理， 这样的话， 他们只能访问这个代理对象， 而不是其他对象， 而代理对象重写了close 方法
	public PackConnection(Connection connection, LinkedList<Connection> pool) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.pool = pool;
	}
	
	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("如果你看见这句话就说明close()方法被重写了");
		pool.add(connection);
	}

}
