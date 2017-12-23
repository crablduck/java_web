package com.dream.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//��ݿ⹤�߰�
public class DBUtils {

	private static String url;
	private static String username;
	private static String password;
	
	//��̬�����
	static{
		
		try {
			//�������
			Class.forName("com.mysql.jdbc.Driver");
			
			//���ҵ������ļ��еĿ�����Ϣ
			Properties p = new Properties();
			//����src�ļ����µ�ָ���ļ�
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
		
		
		/**
		 * ��ݿ����Ѿ������ű?student��class��
		 * cmd�������ô�������ʵ�����ѧ����Զ���Ӱ༶����ɾ��ѧ����Զ����ٰ༶����
		 * ʹ��JDBC��Ӱ༶�����ѧ��ɾ��ѧ��
		 * 
		 * ������һ�£����ô�������ֻ��JDBCȥʵ�����Ϲ���
		 */
		
	}
	
//	private static Connection connection;
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
		//��ȷ�ر���Դ (����˳��connection��statement��resultSet) (���˳�򣺷�����)
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
		connection = local.get();
		if(connection != null){
			try {
				if(connection.getAutoCommit()){//�ж��Ƿ������trueû������
					connection.close();
					connection = null;
					local.set(null);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//��������
	public static void startTransaction() {
		
		try {
			Connection connection = getConnection();//�ȵõ����Ӷ���
			connection.setAutoCommit(false);//��������
		} catch (SQLException e) {
		}
		
	}
	//�ع�����
	public static void rollback(){
		Connection connection = local.get();
		if(connection != null){
			try {
				connection.rollback();
				connection.close();
				local.set(null);
			} catch (SQLException e) {
			}
		}
	}
	//�ύ����
	public static void commit(){
		Connection connection = local.get();
		if(connection != null){
			try {
				connection.commit();
				connection.close();
				local.set(null);
				connection = null;
			} catch (SQLException e) {
			}
		}
		
	}
}
