package com.dream.service;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dream.db.DBCenter;
import com.dream.dto.StudentDto;
import com.dream.entity.Student;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//区分前端发来的请求具体是要去操作什么功能
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String city = request.getParameter("city");
		String[] loves = request.getParameterValues("loves");//注意：此方法是获得数组
		String repassword = request.getParameter("repassword");//新密码
		
		if("register".equals(action)){//注册功能

			//判断是否可以注册（此用户是否在数据中心里存在）
			boolean bool = true;//记录注册与否的布尔值(true--可以注册，false--不可注册)
			
			ArrayList<Student> stustulist = DBCenter.stulist;
			for (Student stu : stustulist) {
				if(stu.getUsername().equals(username)){
					bool = false;
				}
			}
			//依据注册成功与否去跳转到相应界面
			if(bool){//注册成功 --- 跳转到登录页面
				
				//生成一个学生对象存入到DBCenter
				Student stu = new Student(username, password, sex, city, loves);
				DBCenter.stulist.add(stu);
				
				//转发跳转：
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}else{//注册失败 --- 跳转到注册页面
				
				//转发跳转：
				request.setAttribute("msg", "注册失败");//把键值对设置给当前请求，此键值对的作用域就在此请求内
				request.getRequestDispatcher("register.jsp").forward(request, response);
				
			}

		}else if("initmodify".equals(action)){//修改初始化功能
			
			//获得发送请求的地址
			String url = request.getHeader("Referer");
			
			if(url.endsWith(".jsp" )|| url.endsWith("getstudentlist")){//有效地址
				request.getSession().setAttribute("referer", url);
			}
			
			//1.找到当前用户的学生对象
			for (Student stu : DBCenter.stulist) {
				if(stu.getUsername().equals(username)){
					//2.存入请求
					request.setAttribute("stu", stu);
					//3.跳转到stuInfo.jsp
					request.getRequestDispatcher("stuInfo.jsp").forward(request, response);
				}
			}
		}else if("modify".equals(action)){//修改信息
			
			//1.找到当前用户的学生对象
			Student student = null;
			for (Student stu : DBCenter.stulist) {
				if(stu.getUsername().equals(username)){//找到用户对象了
					student = stu;
				}
			}
			
			//2.修改学生对象
			if(student!=null){//找到学生对象了
				
				student.setSex(sex);
				student.setCity(city);
				student.setLoves(loves);
				request.setAttribute("msg", "修改成功");
				
			}else{//没找到
				request.setAttribute("msg", "修改失败");
			}
			
			request.getRequestDispatcher("StudentServlet?action=initmodify").forward(request, response);
			
			
		}else if("updatepassword".equals(action)){//修改密码
			
			//1.找到当前对象
			username = (String) request.getSession().getAttribute("username");
			Student stu = null;
			for (Student student : DBCenter.stulist) {
				if(student.getUsername().equals(username) && student.getPassword().equals(password)){
					stu = student;
				}
			}
			//2.修改密码
			if(stu!=null){
				stu.setPassword(repassword);
				request.setAttribute("msg", "修改成功");
			}else{
				request.setAttribute("msg", "修改失败");
			}
			request.getRequestDispatcher("repassword.jsp").forward(request, response);
		}else if("getstudentlist".equals(action)){//获得所有学生信息的功能
			
			ArrayList<Student> stulist = DBCenter.stulist;
			
			//加工
			ArrayList<StudentDto> dtolist = new ArrayList<>();
			StringBuffer sb = new StringBuffer();
			for (Student stu : stulist) {
				StudentDto dto = new StudentDto();
				dto.setStu(stu);
				loves = stu.getLoves();
				for (String str : loves) {
					sb.append(str);
					sb.append(",");
				}
				sb.deleteCharAt(sb.length()-1);
				dto.setLoveStr(sb.toString());
				sb.delete(0, sb.length());
				
				dtolist.add(dto);
			}
			
			//存入请求
			request.setAttribute("dtolist", dtolist);
			//转发--跳转
			request.getRequestDispatcher("stulist.jsp").forward(request, response);
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
