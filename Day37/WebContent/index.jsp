<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String username = (String)request.getSession().getAttribute("username");
	String role = (String)request.getSession().getAttribute("role");
%>

<input type="button" value="注销按钮/安全退出" onclick="window.location.href='UserServlet?action=logout'"/>

<h1><%= "student".equals(role)?"学生":"" %> <%= "teacher".equals(role)?"老师":"" %>详情页面</h1>

<h3>欢迎<%=(username!=null)?username:""%> <%= "student".equals(role)?"学生":"" %> <%= "teacher".equals(role)?"老师":"" %></h3>

<%if("student".equals(role)){%>

	<a href="StudentServlet?action=initmodify&username=<%=username%>">修改信息</a><br/>
	<a href="repassword.jsp">修改密码</a>
	
<%}else if("teacher".equals(role)){%>

	<a href="TeacherServlet?action=initmodify&username=<%=username%>">修改信息</a><br/>
	<a href="#">修改密码</a><br/>
	<a href="StudentServlet?action=getstudentlist">管理学生信息</a><br/>
	
<%}%>

</body>
</html>