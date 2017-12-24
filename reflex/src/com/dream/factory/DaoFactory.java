package com.dream.factory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

import com.dream.dao.UserDao4MySql;
import com.dream.dao.UserDao4Oracle;
import com.dream.i.UserDao;

public class DaoFactory {
	
	private static DaoFactory factory = new DaoFactory();
	private static UserDao dao;
	
	static{
		Properties p = new Properties();
		
		try {
			p.load(DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties"));
			String daoUrl = (String) p.get("daoUrl");
			
			//反射
			Class<?> class1 = Class.forName(daoUrl);
			Constructor<?> constructor = class1.getConstructor();
			dao = (UserDao) constructor.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private DaoFactory(){}
	
	public static DaoFactory getInstance(){
		return factory;
	}
	
	public UserDao getDao(){
		return dao;
	}
	
	

}
