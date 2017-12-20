package com.xjw.cblob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.xjw.util.DBUtils;

public class Blob {

	@Test
	public void blobWrite() throws SQLException, FileNotFoundException{
		
		Connection connection = DBUtils.getConnection();
		String sql = "insert into cblob(img) values(?)";
		
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		
		File file = new File("fbb.jpg");
		FileInputStream fileInputStream = new FileInputStream(file);
		
		prepareStatement.setBinaryStream(1, fileInputStream, (int)file.length());
		
		prepareStatement.executeUpdate();
		
		DBUtils.close(connection, prepareStatement, null);
		
	}
	
	@Test
	public void blobRead() throws SQLException, IOException{
		
		Connection connection = DBUtils.getConnection();
		String sql = "select img from cblob where id=4";
		
		Statement statement = connection.createStatement();
		ResultSet executeQuery = statement.executeQuery(sql);
		
		if(executeQuery.next()){
			//一定要在next里面， 因为只有next后才有一行数据
			InputStream inputStream = executeQuery.getBinaryStream("img");
			FileOutputStream fileOutputStream = new FileOutputStream("copy.jpg");
			
			byte[] c = new byte[1024];
			
			int len = 0;
			while((len=inputStream.read(c))!=-1){
				fileOutputStream.write(c, 0, len);
			}
			inputStream.close();
			fileOutputStream.close();
		}
		
		DBUtils.close(connection, statement, executeQuery);
	}
}
