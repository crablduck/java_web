package com.dream.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

	//构造方法
	public MyHttpSessionAttributeListener() {
	}

	//session删除属性时调用
	public void attributeRemoved(HttpSessionBindingEvent event)  { 

		HttpSession session = event.getSession();

		System.out.println(session.getId() + "删除属性");

	}

	//session添加属性时调用
	public void attributeAdded(HttpSessionBindingEvent event)  { 

		HttpSession session = event.getSession();

		System.out.println(session.getId() + "添加属性");
	}

	//session属性改变时调用
	public void attributeReplaced(HttpSessionBindingEvent event)  { 

		HttpSession session = event.getSession();

		System.out.println(session.getId() + "改变属性");
	}

}
