<%@page import="com.dream.entity.Teacher"%>
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
		String msg = (String)request.getAttribute("msg");
		Teacher tea =  (Teacher)request.getAttribute("tea");
		String url = (String)request.getSession().getAttribute("referer");
	%>
	
	<%= (msg!=null)?msg:"" %>

	<form action="TeacherServlet?action=modify" method="post">

		<input type="hidden" name="username" value="<%= tea.getUsername() %>"/>	
		
		账户：<%= tea.getUsername() %><br/>
		性别：
			
			<input type="radio" name="sex" value="man" <%= (tea.getSex().equals("man")?"checked='checked'":"") %> />男
			<input type="radio" name="sex" value="woman" <%= (tea.getSex().equals("woman")?"checked='checked'":"") %>/>女
			<br/>
		地址：
			<select name="city">
				<option value="beijing"  <%= (tea.getCity().equals("beijing")?"selected='selected'":"") %>>北京</option>
				<option value="shanghai" <%= (tea.getCity().equals("shanghai")?"selected='selected'":"") %>>上海</option>
				<option value="shenzhen" <%= (tea.getCity().equals("shenzhen")?"selected='selected'":"") %>>深圳</option>
				<option value="chengdu"  <%= (tea.getCity().equals("chengdu")?"selected='selected'":"") %>>成都</option>
			</select>
			<br/>
	
		<input type="submit" value="修改"/>
		
		<input type="button" value="取消" onclick="mygo()"/>
	</form>
	
	<script type="text/javascript">
	
		function mygo(){
			window.location.href = "<%=url%>";
		}
	
	</script>
	
</body>
</html>