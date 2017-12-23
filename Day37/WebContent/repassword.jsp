<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>修改密码</h3>

	<%
		String msg = (String)request.getAttribute("msg");
	
		//String url = (String)request.getHeader("Referer");
		//request.getSession().setAttribute("url", url);
	
	%>
	<%= msg!=null?msg:"" %>


	<form action="StudentServlet" method="post">
		
		<input type="hidden" name="action" value="updatepassword"/>
	
		原密码：<input type="password" name="password"/><br/>
		新密码：<input type="password" name="repassword"/><br/>
		确认新密码：<input type="password"/><br/>
		
		<input type="submit" value="修改"/>
		<br/>
		<input type="button" value="取消" onclick="mygo()"/>
	</form>
	<script type="text/javascript">
		function mygo(){
			window.location.href = "index.jsp";
		}
	
	</script>


</body>
</html>