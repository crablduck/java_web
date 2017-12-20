package com.xjw.pool3;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 谢嘉伟
 * 实现了连接池， 可是有个问题， 别人如果没有用PoolUtil.close() 而是直接用connection.close()那么我们池子的连接将会逐渐减少
 * 解决办法是：重写connection.close写成PoolUtil.close() 这样就可以避免
 */
public class PoolUtil {

	private static PackPool packPool = new PackPool();
	
	public static Connection getConnection(){
		try {
			return packPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection connection){
		packPool.release(connection);
	}
}
