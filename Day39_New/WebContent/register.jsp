<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		span{color: red;}
	</style>
</head>
<body>

	${msg}

	<h1>注册页面</h1>
	
	<form action="StudentServlet?action=register" method="post" onsubmit="return sub()">
	
		账户：<input id="username" name="username" type="text" placeholder="请输入账户..."/><span></span><br/>
		密码：<input id="password" name="password" type="password" placeholder="请输入密码..."/><span></span><br/>
		确认密码：<input id="repassword" type="password" placeholder="请输入密码..."/><span></span><br/>
		性别：
			<input type="radio" name="sex" value="man" checked="checked"/>男
			<input type="radio" name="sex" value="woman"/>女
			<br/>
		地址：
			<select name="city">
				<option value="beijing">北京</option>
				<option value="shanghai">上海</option>
				<option value="shenzhen">深圳</option>
				<option value="chengdu">成都</option>
			</select>
			<br/>
		爱好：<br/>
			<input name="loves" type="checkbox" value="football"/>足球<br/>
			<input name="loves" type="checkbox" value="basketball"/>篮球<br/>
			<input name="loves" type="checkbox" value="shopping"/>购物<br/>
	
		<input type="submit" value="注册"/>
	</form>
	
	<script type="text/javascript">
	
		function sub(){
			
			//分别得到账户、密码、确认密码的对象
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			var repassword = document.getElementById("repassword");
			
			//获得span的对象
			var span1 = document.getElementsByTagName("span")[0];
			var span2 = document.getElementsByTagName("span")[1];
			var span3 = document.getElementsByTagName("span")[2];
			
			span1.innerText = "";
			span2.innerText = "";
			span3.innerText = "";
			
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
			//3.验证确认密码是否为空
			if(repassword.value.trim() == ""){
				span3.innerText = "确认密码不能为空";
				bool = false;
			}else{
				//4.验证密码是否和确认密码一致
				if(password.value != repassword.value){
					span3.innerText = "密码和确认密码不一致";
					bool = false;
				}
			}
			return bool;
		}
	</script>

</body>
</html>