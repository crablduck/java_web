package com.dream.service;

import com.dream.biz.UserBiz;

public class UserServlet {

	public static void main(String[] args) {
		
		UserBiz biz = new UserBiz();
		biz.add();
	}
}
