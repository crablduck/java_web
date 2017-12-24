package com.dream.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClearSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClearSessionServlet() {
    }
    
    @Override
    public void init(final ServletConfig config) throws ServletException {
    	
    	//每过1秒监听所有闲置的Session对象 --- 需要定时器
    	Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
			@Override
			public void run() {
				
				//1.每次去取的map对象
				HashMap<String, HttpSession> map = (HashMap<String, HttpSession>) config.getServletContext().getAttribute("map");
				//2.遍历map内数据---String sessionId/HttpSession session
				
				if(map != null){
				Set<Entry<String, HttpSession>> entrySet = map.entrySet();
					for (Entry<String, HttpSession> entry : entrySet) {
						String key = entry.getKey();//seesionId
						HttpSession session = entry.getValue();
						//3.获取最后使用时间  getLastAccessedTime
						long lastAccessedTime = session.getLastAccessedTime();
						//4.获取当前时间
						Date date = new Date();
						long time = date.getTime();
						//5.判断是否超过10秒
						if((time - lastAccessedTime) > (10*1000)){
							//6.如果超过10秒就删除  invalidate
							session.invalidate();
							map.remove(key);
							System.out.println("删除了:" + key);
						}
					}
				}
				}
		},0, 1000);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
