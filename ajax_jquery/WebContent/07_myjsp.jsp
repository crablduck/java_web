<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	
	String username = (String)request.getParameter("username");


	Thread.sleep(3000);


	if(username.equals("hhy")){//不可以注册
		response.getWriter().write("yes");
	}else{//可以注册
		response.getWriter().write("no");
	}
	
%>