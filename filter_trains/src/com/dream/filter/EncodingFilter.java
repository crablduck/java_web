package com.dream.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 编码过滤器
 * （注意：编码过滤器必须是首要的过滤器）
 */
public class EncodingFilter implements Filter {

    public EncodingFilter() {
    	System.out.println("编码过滤器 --- 构造方法");
    }

	public void destroy() {
		System.out.println("编码过滤器 --- 销毁方法");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("编码过滤器 --- doFilter -- 开始工作"); 
		
		//设置编码(请求、响应) --- 仅仅对post请求方式有用
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//设置编码格式（请求） --- 对Get请求方式设置编码格式
		MyHttpServletRequestWapper reqWapper = new MyHttpServletRequestWapper(req);
		
		chain.doFilter(reqWapper, resp);
		
		System.out.println("编码过滤器 --- doFilter -- 结束工作"); 
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("编码过滤器 --- 初始化方法");
	}
}

/**
 * get设置编码的思路
 * 		1.创建请求包装类
 * 		2.注意往下传递的请求对象就是我们这个包装类的对象
 * 		3.只要想获得请求的信息，就用父类对象--super
 * 		4.转码 iso-8859-1 ---》 UTF-8
 */



//去创建自己的HttpServletRequest的包装类，继承HttpServletRequest的包装类
class MyHttpServletRequestWapper extends HttpServletRequestWrapper{

	//创建对象时必须闯入一个请求对象，父类会自动实现创建request接口的方法
	public MyHttpServletRequestWapper(HttpServletRequest request) {
		super(request);
	}
	
	//重写getParameter
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);//值
		String method = super.getMethod();//请求格式
		
		if("GET".equals(method)){
			//设置编码格式 -- GET
			if(value != null){
				try {
					value = new String(value.getBytes("iso-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
				}
			}
			
		}
		
		return value;
	}
	
}




