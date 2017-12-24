package com.dream.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sun.xml.internal.ws.transport.http.HttpMetadataPublisher;

/**
 * HttpSessionListener的监听器
 * 	所有的监听器创建步奏：
 * 		1.创建一个类，实现响应的监听器
 * 		2.在配置文件中设置相应的代码 
 * 			  <listener>
			    <listener-class>com.dream.listener.MyHttpSessionListener</listener-class>
			  </listener>
			  
	监听器生命周期：
			1.创建 --- 项目启动时
			2.销毁 --- 项目结束
			
	Session生命周期：
			1.创建 --- jsp内置就创建，Servlet调用Session就创建
			2.销毁 --- （注意：项目重启不会销毁Session）
				2.1 配置文件中销毁(数字是以分钟为单位)
					  <session-config>
					  	<session-timeout>1</session-timeout>
					  </session-config>
				2.2 session.invalidate();直接销毁此session对象
			
	html 不会创建Session
	jsp 会创建Session
	为什么？jsp底层是初始化创建9大内置对象，而其中就有Session
			  
	监听器作业：
			1.监听项目中闲置十秒的Seesion对象，超过10秒就直接删除掉  
				思路：项目启动时，就启动一个ClearSessionServlet
 */
public class MyHttpSessionListener implements HttpSessionListener {

	//构造方法
    public MyHttpSessionListener() {
    	System.out.println("MyHttpSessionListener --- 构造方法");
    }

    HashMap<String, HttpSession> map = new HashMap<>();
    
    //Session创建时调用
    //HttpSessionEvent --- Session事件对象
    public void sessionCreated(HttpSessionEvent event)  { 
    	
    	HttpSession session = event.getSession();//获取创建的对象
    	map.put(session.getId(), session);//每创建一个Session就存入此集合中
    	
    	//获得ServletContext(全局)对象，再把map存入到此对象中
    	ServletContext servletContext = event.getSession().getServletContext();
    	servletContext.setAttribute("map", map);
    	
    	
    	System.out.println("MyHttpSessionListener --- sessionCreated：" + session.getId());
    }

    //Session销毁时调用
    public void sessionDestroyed(HttpSessionEvent event)  { 
    	System.out.println("MyHttpSessionListener --- sessionDestroyed：" + event.getSession().getId());
    }
	
}
