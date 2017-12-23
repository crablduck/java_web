<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.dream.entity.Student"%>
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
		Student stu =  (Student)request.getAttribute("stu");
		String url = (String)request.getSession().getAttribute("referer");
	%>
	
	<%= (msg!=null)?msg:"" %>

	<form action="StudentServlet?action=modify" method="post">

		<input type="hidden" name="username" value="<%= stu.getUsername() %>"/>	
		
		账户：<%= stu.getUsername() %><br/>
		性别：
			
			<input type="radio" name="sex" value="man" <%= (stu.getSex().equals("man")?"checked='checked'":"") %> />男
			<input type="radio" name="sex" value="woman" <%= (stu.getSex().equals("woman")?"checked='checked'":"") %>/>女
			<br/>
		地址：
			<select name="city">
				<option value="beijing"  <%= (stu.getCity().equals("beijing")?"selected='selected'":"") %>>北京</option>
				<option value="shanghai" <%= (stu.getCity().equals("shanghai")?"selected='selected'":"") %>>上海</option>
				<option value="shenzhen" <%= (stu.getCity().equals("shenzhen")?"selected='selected'":"") %>>深圳</option>
				<option value="chengdu"  <%= (stu.getCity().equals("chengdu")?"selected='selected'":"") %>>成都</option>
			</select>
			<br/>
			<%
				List<String> list = Arrays.asList(stu.getLoves());
			%>
			
		爱好：<br/>
		
			<input name="loves" type="checkbox" value="football" <%= (list.contains("football")?"checked='checked'":"") %>/>足球<br/>
			<input name="loves" type="checkbox" value="basketball" <%= (list.contains("basketball")?"checked='checked'":"") %>/>篮球<br/>
			<input name="loves" type="checkbox" value="shopping" <%= (list.contains("shopping")?"checked='checked'":"") %>/>购物<br/>
	
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