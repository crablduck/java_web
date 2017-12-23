<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.dream.entity.Student"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生修改页面</title>
</head>
<body>

	${msg}

	<form action="StudentServlet?action=modify" method="post">

		<input type="hidden" name="id" value="${stuDto.stu.id}"/>	
		
		账户：${stuDto.stu.username}<br/>
		性别：
			
			<input type="radio" name="sex" value="man" ${(stuDto.stu.sex eq "man")?"checked='checked'":""} />男
			<input type="radio" name="sex" value="woman" ${(stuDto.stu.sex eq "woman")?"checked='checked'":""}/>女
			<br/>
		地址：
			<select name="city">
			
				<option value="beijing"  ${(stuDto.stu.city eq "beijing")?"selected='selected'":""}>北京</option>
				<option value="shanghai" ${(stuDto.stu.city eq "shanghai")?"selected='selected'":""}>上海</option>
				<option value="shenzhen" ${(stuDto.stu.city eq "shenzhen")?"selected='selected'":""}>深圳</option>
				<option value="chengdu"  ${(stuDto.stu.city eq "chengdu")?"selected='selected'":""}>成都</option>
			</select>
			<br/>
			
		爱好：<br/>
			
			<input name="loves" type="checkbox" value="football" <c:if test="${fn:contains(stuDto.loveStr,'football')}"> checked='checked' </c:if>/>足球<br/>
			<input name="loves" type="checkbox" value="basketball" <c:if test="${fn:contains(stuDto.loveStr,'basketball')}"> checked='checked' </c:if>/>篮球<br/>
			<input name="loves" type="checkbox" value="shopping" <c:if test="${fn:contains(stuDto.loveStr,'shopping')}"> checked='checked' </c:if>/>购物<br/>
	
		<input type="submit" value="修改"/>
		
		<input type="button" value="取消" onclick="mygo()"/>
	</form>
	
	<script type="text/javascript">
	
		function mygo(){
			window.history.go(-1);
		}
	
	</script>

</body>
</html>