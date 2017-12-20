package com.xjw.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet_init
 */
public class servlet_init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String encoding ;
	
	
    public servlet_init() {
    	System.out.println("servlet_init.servlet_init()");
	}

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	encoding = config.getInitParameter("encoding");
        System.out.println("this is a init string"+encoding);
    }
    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	System.out.println(encoding);
    	arg0.setCharacterEncoding(encoding);
    	arg1.setContentType("text/html; charset="+encoding);
    	System.out.println("servlet_init.service()");
    }
    
    @Override 
    public void destroy() {
    	// TODO Auto-generated method stub
    	System.out.println("servlet_init.destroy()"); 
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
