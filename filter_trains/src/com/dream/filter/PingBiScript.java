package com.dream.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 屏蔽脚本过滤器
 * 思路：创建请求的包装类，替换
 * 	<h1>xxx<h1/>  --> '&lt;' ==> '<'   '&gt;'==>'>'
 * 	&lt;h1&gt;xxx&lt;sh1/&gt;
 * 
 */
public class PingBiScript implements Filter {

    public PingBiScript() {
    }

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		MyPingBiScriptWapper myPingBiScriptWapper = new MyPingBiScriptWapper(req);
		
		chain.doFilter(myPingBiScriptWapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
class MyPingBiScriptWapper extends HttpServletRequestWrapper{

	public MyPingBiScriptWapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		
		String value = super.getParameter(name);
		value = value.replaceAll( "<","&lt;");
		value = value.replaceAll( ">","&gt;");
		
		
		return value;
	}
	
}
