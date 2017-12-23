<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		span{color: red}
	</style>
</head>
<body>

	<!-- 获取Cookie，如果有Cookie--直接跳转到学生详情页面 -->
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			
			boolean bool = false;
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("username")){
					request.getSession().setAttribute("username", cookie.getValue());
					bool = true;
				}
				if(cookie.getName().equals("role")){
					request.getSession().setAttribute("role", cookie.getValue());
				}
			}
			if(bool){
				response.sendRedirect("index.jsp");
			}
		}
	
	%>



	<!-- 获取值 -->
	<%
		String msg = (String)request.getAttribute("msg");
	%>
	<!-- 展示值 -->
	<%= (msg != null)?msg:"" %>
	

	<h1>登录页面</h1>
	
	<form action="UserServlet" method="post" onsubmit="return sub()">
	
	
		<input type="hidden" name="action" value="login"/>
	
		账号：<input id="username" name="username" type="text" placeholder="请输入账号"/><span></span><br/>
		密码：<input id="password" name="password" type="password" placeholder="请输入密码"/><span></span><br/>
		校验码：<input name="checkcode" type="text" placeholder="请输入校验码"/>
				<img id="img" src="CheckServlet" width="120px" height="50px" onclick="changeImg(this)">
				<input type="button" value="刷新" onclick="changeImg(document.getElementById('img'))"/>
				<br/>
				
		角色：
		<select name="role">
			<option value="student">学生</option>
			<option value="teacher">老师</option>
		</select>
		<br/>
		<input name="remenberme" type="checkbox"/>记住我<br/>
		
		<input type="submit" value="登录"/>
	</form>
	
	<script type="text/javascript">
		
		function sub(){
			//分别得到账户、密码的对象
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			
			//获得span的对象
			var span1 = document.getElementsByTagName("span")[0];
			var span2 = document.getElementsByTagName("span")[1];
			
			span1.innerText = "";
			span2.innerText = "";
			
			var bool = true;//记录是否可提交
			
			//1.验证账户是否为空
			if(username.value.trim() == ""){
				span1.innerText = "账户不能为空";
				bool = false;
			}
			//2.验证密码是否为空
			if(password.value.trim() == ""){
				span2.innerText = "密码不能为空";
				bool = false;
			}
			
			return bool;
		}
	
		function changeImg(obj){
			var date = new Date();
			obj.src = "CheckServlet?time=" + date.toGMTString();
		}
		
	</script>
	
	
</body>
</html>