package com.dream.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器的创建步奏：
 * 		1.创建一个类，实现Filter接口
 * 		2.在配置文件中配置相关信息(注意配置*是指所有请求都要经过此过滤器)
 * 	      <filter>
		    <filter-name>SecurityFilter</filter-name>
		    <filter-class>com.dream.filter.SecurityFilter</filter-class>
		    <init-param>
		    	<param-name>key</param-name>
		    	<param-value>value</param-value>
		    </init-param>
		  </filter>
		  <filter-mapping>
		    <filter-name>SecurityFilter</filter-name>
		    <url-pattern>/*</url-pattern>
		  </filter-mapping>
 */

//安全过滤器
public class SecurityFilter implements Filter {

	//构造方法
    public SecurityFilter() {
    	
    	System.out.println("安全过滤器 --- 构造方法");
    }

    //销毁方法
	public void destroy() {
		System.out.println("安全过滤器 --- 销毁方法");
	}

	/**
	 * 触发过滤器时就调用
	 * request --- 类型是HttpServletRequest的父接口
	 * response --- 类型是HttpServletResponse的父接口
	 * chain --- 过滤器链
	 * 
	 * 只要调用此方法，就会把请求和相应对象传递个下一个过滤器，如果此过滤器为最后一个过滤器了，则会传递给请求指定的Servlet
	 * chain.doFilter(request, response);
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("安全过滤器 --- doFilter --- 开始工作");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();//获取请求的地址
		
		if(uri.endsWith("login.jsp") || "login".equals(req.getParameter("action"))){//放行
			chain.doFilter(request, response);
		
		}else{
			//还要再查询下是否有凭证
			String username = (String) req.getSession().getAttribute("username");
			if(username == null){//不放行
				resp.sendRedirect("login.jsp");
			}else{//放行
				chain.doFilter(request, response);
			}
		}
		
		
		System.out.println("安全过滤器 --- doFilter --- 结束工作");
	}

	//初始化方法
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("安全过滤器 --- 初始化方法");
	}

}
