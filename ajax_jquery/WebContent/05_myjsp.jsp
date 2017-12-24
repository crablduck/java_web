<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	
	String username = (String)request.getParameter("username");
	if(username.equals("hhy")){//不可以注册
		response.getWriter().write("1");
	}else{//可以注册
		response.getWriter().write("0");
	}
	
%>