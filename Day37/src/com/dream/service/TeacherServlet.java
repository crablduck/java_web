package com.dream.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.db.DBCenter;
import com.dream.entity.Student;
import com.dream.entity.Teacher;

public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//区分前端发来的请求具体是要去操作什么功能
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String city = request.getParameter("city");
		
		if("initmodify".equals(action)){//修改初始化
			
			//获得发送请求的地址
			String url = request.getHeader("Referer");
			if(url.endsWith(".jsp")){//有效地址
				request.getSession().setAttribute("referer", url);
			}
			
			//1.找到当前用户的老师对象
			for (Teacher tea : DBCenter.tealist) {
				if(tea.getUsername().equals(username)){
					//2.存入请求
					request.setAttribute("tea",tea);
					//3.跳转到teaInfo.jsp
					request.getRequestDispatcher("teaInfo.jsp").forward(request, response);
				}
			}
			
		}else if("modify".equals(action)){//修改功能
			
			//1.找到当前用户的老师对象
			Teacher teacher = null;
			for (Teacher tea : DBCenter.tealist) {
				if(tea.getUsername().equals(username)){//找到用户对象了
					teacher = tea;
				}
			}
			
			//2.修改老师对象
			if(teacher!=null){//找到老师对象了
				
				teacher.setSex(sex);
				teacher.setCity(city);
				request.setAttribute("msg", "修改成功");
				
			}else{//没找到
				request.setAttribute("msg", "修改失败");
			}
			
			request.getRequestDispatcher("TeacherServlet?action=initmodify").forward(request, response);
			
			
			
		}
		



	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
