package com.dream.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.db.DBCenter;
import com.dream.entity.Student;
import com.dream.entity.Teacher;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//区分前端发来的请求具体是要去操作什么功能
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");
		String remenberme = request.getParameter("remenberme");//记住我
		String role = request.getParameter("role");//角色
		

		if("login".equals(action)){//登录功能

			//取出存放的校验码
			StringBuffer sb = (StringBuffer) request.getSession().getAttribute("cc");
			if(sb.toString().equals(checkcode)){//验证校验码
				
				//依据角色判断是否可以登录
				boolean bool = false;//记录登录状态(false--登录失败，true--登录成功)
				if("student".equals(role)){
					ArrayList<Student> stulist = DBCenter.stulist;
					for (Student stu : stulist) {
						if(stu.getUsername().equals(username) && stu.getPassword().equals(password)){
							bool = true;
						}
					}
				}else if("teacher".equals(role)){
					ArrayList<Teacher> tealist = DBCenter.tealist;
					for (Teacher tea : tealist) {
						if(tea.getUsername().equals(username) && tea.getPassword().equals(password)){
							bool = true;
						}
					}
				}
				
				//依据登录的布尔值来去做具体操作
				if(bool){//登录成功 --- 跳转到详情页面
					
					//判断是否选中“记住我”
					if(remenberme != null){
						//添加Cookie --- 存放登录成功以后的凭证
						Cookie cookie1 = 
								new Cookie("username",URLEncoder.encode(username, "UTF-8"));
						cookie1.setMaxAge(60*60);
						response.addCookie(cookie1);
						
						Cookie cookie2 = 
								new Cookie("role",URLEncoder.encode(role, "UTF-8"));
						cookie2.setMaxAge(60*60);
						response.addCookie(cookie2);
					}
					
					//跳转 --- 重定向
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("role",role);
					response.sendRedirect("index.jsp");
				}else{//登录失败 --- 跳转到欢迎页面
					//转发跳转：
					request.setAttribute("msg", "登陆错误");//把键值对设置给当前请求，此键值对的作用域就在此请求内
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}else{
				//转发跳转：
				request.setAttribute("msg", "校验码错误");//把键值对设置给当前请求，此键值对的作用域就在此请求内
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if("logout".equals(action)){//注销Cookie
			
			//删除Cookie
			Cookie cookie1 = 
					new Cookie("username","");
			cookie1.setMaxAge(0);
			response.addCookie(cookie1);
			
			//删除Cookie
			Cookie cookie2 = 
					new Cookie("role","");
			cookie2.setMaxAge(0);
			response.addCookie(cookie2);
			
			//跳转到登陆页面
			response.sendRedirect("login.jsp");
			
		}
		
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
