package com.dream.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.biz.TeaBiz;
import com.dream.dto.StudentDto;
import com.dream.entity.Page;
import com.dream.entity.Teacher;

public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TeaBiz teaBiz = new TeaBiz();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	
		String action = request.getParameter("action");
		if("showlist".equals(action)){
			
			Page page = new Page(1, 7, teaBiz.getTotalPage(7), "TeacherServlet?action=showlist&current=");
			String current = request.getParameter("current");
			if(current != null){//能取到页数了
				page.setCurrentPage(Integer.parseInt(current));
			}
			ArrayList<Teacher> dtolist = teaBiz.getTeaList(page.getCurrentPage(),page.getPageSize());
			page.setList(dtolist);
			
			request.setAttribute("page", page);
			request.getRequestDispatcher("tealist.jsp").forward(request, response);
		}
	}
}
