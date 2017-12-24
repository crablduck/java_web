package com.dream.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.db.DBCenter;
import com.dream.entity.City;

public class UserServlet_Old extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String province = request.getParameter("province");
	
		if("isRegister".equals(action)){
			
			if("hhy".equals(username)){//不可以注册
				
//				response.getWriter().write("很抱歉，用户名已注册");
				response.getWriter().write("1");
			}else{
//				response.getWriter().write("恭喜您，用户名可用");
				response.getWriter().write("0");
			}
		}else if("updateprovince4city".equals(action)){
			//根据具体的省份找出相应的城市
			ArrayList<City> list = DBCenter.map.get(province);
			
			StringBuffer sb = new StringBuffer();
			for (City city : list) {
				sb.append("<option value='" +city.getId()+ "'>" + city.getCityName() + "</option>");
			}
			response.getWriter().write(sb.toString());
		}
		
		
		
	}

}
