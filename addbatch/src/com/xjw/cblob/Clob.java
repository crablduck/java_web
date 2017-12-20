package com.xjw.cblob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.xjw.util.DBUtils;import sun.launcher.resources.launcher_zh_TW;

public class Clob {

	@Test
	public void clobWrite() throws SQLException, FileNotFoundException{
		Connection connection = DBUtils.getConnection();
		
		String sql = "insert into cblob(clob) values(?)";
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		
			File file = new File("info.txt");
			FileReader fileReader = new FileReader(file);
			prepareStatement.setCharacterStream(1, fileReader, (int)file.length());
			prepareStatement.executeUpdate();
			DBUtils.close(connection, prepareStatement, null);
	}
	
	@Test
	public void clobRead() throws SQLException, IOException{
		Connection connection = DBUtils.getConnection();
		
		String sql = "select clob from cblob where id=3";
		
		Statement statement = connection.createStatement();
		ResultSet query = statement.executeQuery(sql);
		
		if(query.next()){
			Reader characterStream = query.getCharacterStream("clob");
			
			char[] c = new char[1024];
			int len = 0;
			while((len=characterStream.read(c)) != -1 ){
				System.out.println(new String(c, 0, len));
			}
			characterStream.close();
		}
		DBUtils.close(connection, statement, query);
	}
	
	
}
