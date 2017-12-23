<%@page import="com.dream.dto.StudentDto"%>
<%@page import="com.dream.entity.Student"%>
<%@page import="java.util.ArrayList"%>
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
		ArrayList<StudentDto> dtolist =  (ArrayList)request.getAttribute("dtolist");
	%>
	
	<table border="1">
		<tr>
			<th>姓名</th><th>性别</th><th>城市</th><th>爱好</th><th>操作</th>
		</tr>
		
		<%for(StudentDto dto:dtolist){%>
			<tr>
				<td><%= dto.getStu().getUsername() %></td>
				<td><%= dto.getStu().getSex() %></td>
				<td><%= dto.getStu().getCity() %></td>
				<td><%= dto.getLoveStr()%></td>
				<td>
					<a href="StudentServlet?action=initmodify&username=<%= dto.getStu().getUsername() %>">修改</a>|<a href="#">删除</a>
				</td>
			</tr>
		
		<%}%>
	
	</table>
	


</body>
</html>