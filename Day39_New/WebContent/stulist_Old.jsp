<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>用户名</th>
			<th>性别</th>
			<th>城市</th>
			<th>爱好</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${dtolist}" var="dto">
		
			<tr>
				<td>${dto.stu.username}</td>
				<td>${dto.stu.sex}</td>
				<td>${dto.stu.city}</td>
				<td>${dto.loveStr}</td>
				<td>
					<a href="StudentServlet?action=initupdate&id=${dto.stu.id}">修改</a>
					<a href="StudentServlet?action=delete&id=${dto.stu.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
				<input type="button" value="添加" onclick="window.location.href='register.jsp'"/>
			</td>
		</tr>
	</table>
	<a href="StudentServlet?action=showlist&current=1">首页</a>
	
	<c:if test="${currentPage>1}">
		<a href="StudentServlet?action=showlist&current=${currentPage-1}">上一页</a>
	</c:if>
	<c:if test="${currentPage<totalPage}">
		<a href="StudentServlet?action=showlist&current=${currentPage+1}">下一页</a>
	</c:if>
	<a href="StudentServlet?action=showlist&current=${totalPage}">尾页</a>

</body>
</html>