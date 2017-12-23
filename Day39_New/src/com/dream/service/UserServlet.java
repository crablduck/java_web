package com.dream.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.biz.StuBiz;
import com.dream.entity.Student;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StuBiz stuBiz = new StuBiz();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获得请求信息
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
	
		if("login".equals(action)){
			
			Student stu = stuBiz.getStuByUsernamePassword(username,password);
			
			if(stu != null){
				
				request.getSession().setAttribute("user", stu);
				request.setAttribute("msg", "登陆成功");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}else{
				request.setAttribute("msg", "登陆失败");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			
		}
	
	}

}
