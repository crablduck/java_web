package com.dream.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.biz.StuBiz;
import com.dream.dto.StudentDto;
import com.dream.entity.Page;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StuBiz stuBiz = new StuBiz();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	
		//获取请求信息
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String city = request.getParameter("city");
		String[] loves = request.getParameterValues("loves");
	
		if("showlist".equals(action)){//展示学生列表功能
			
			Page page = new Page(1, 7, stuBiz.getTotalPage(7), "StudentServlet?action=showlist&current=");
			
			String current = request.getParameter("current");
			if(current != null){//能取到页数了
				page.setCurrentPage(Integer.parseInt(current));
			}
			
			ArrayList<StudentDto> dtolist = stuBiz.getStuList(page.getCurrentPage(),page.getPageSize());
			page.setList(dtolist);
			
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("stulist.jsp").forward(request, response);
			
		}else if("register".equals(action)){//注册功能
			
			boolean bool = stuBiz.isRegister(username);
			
			if(bool){//可以注册
				
				bool = stuBiz.add(username,password,sex,city,loves);
				if(bool){
					request.getRequestDispatcher("StudentServlet?action=showlist").forward(request, response);
				}else{//注册失败
					request.setAttribute("msg", "注册失败");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}else{//不可以注册
				
				request.setAttribute("msg", "用户名重复");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			
		}else if("delete".equals(action)){//删除功能
			
			boolean bool = stuBiz.delete(id);
			
			if(bool){
				request.getRequestDispatcher("StudentServlet?action=showlist").forward(request, response);
			}
			
		}else if("initupdate".equals(action)){//修改初始化功能
			StudentDto stuDto = stuBiz.getStuById(id);
			request.setAttribute("stuDto", stuDto);
			request.getRequestDispatcher("stuInfo.jsp").forward(request, response);
			
		}else if("modify".equals(action)){//修改功能
			
			boolean bool = stuBiz.update(id,sex,city,loves);
			if(bool){
				request.getRequestDispatcher("StudentServlet?action=showlist").forward(request, response);
			}else{
				request.setAttribute("msg", "修改失败");
				request.getRequestDispatcher("stuInfo.jsp").forward(request, response);
			}
			
		}
	
	
	}

}
