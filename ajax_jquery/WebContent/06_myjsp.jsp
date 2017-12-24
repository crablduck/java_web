<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	
	String username = (String)request.getParameter("username");
	String password = (String)request.getParameter("password");
	
	System.out.println(username + " -- " + password);
%>