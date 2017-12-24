package com.dream.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到请求里的数据
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		
		
		if("login".equals(action)){//登录功能
			
			if("hhy".equals(username) && "123123".equals(password)){//登录成功
				
				//存入凭证
				request.getSession().setAttribute("username", username);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{//登录失败
				request.setAttribute("msg", "登录失败");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			
			
		}
		
	
	
	}

}
