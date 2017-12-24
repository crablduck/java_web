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
 * 屏蔽电话号码
 * 思路：和屏蔽脚本的思路是一模一样的
 */
public class PingBiPhone implements Filter {

    public PingBiPhone() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		MyPingBiPhoneWapper myPingBiPhoneWapper = new MyPingBiPhoneWapper(req);
		
		chain.doFilter(myPingBiPhoneWapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
class MyPingBiPhoneWapper extends HttpServletRequestWrapper{

	public MyPingBiPhoneWapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		
		String value = super.getParameter(name);
		value = value.replaceAll("1[0-9]{10}", "1**********");
		
		return value;
	}
	
}
