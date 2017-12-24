package com.dream.biz;

import com.dream.dao.UserDao4MySql;
import com.dream.dao.UserDao4Oracle;
import com.dream.factory.DaoFactory;
import com.dream.i.UserDao;

public class UserBiz {

//	UserDao4MySql dao = new UserDao4MySql();
//	UserDao4Oracle dao = new UserDao4Oracle();
	
	UserDao dao = DaoFactory.getInstance().getDao();
	
	public void add(){
		dao.add();
	}
	
	
}
