package com.dream.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheckServlet extends HttpServlet {
       
	private static final long serialVersionUID = -4698475009810590699L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//画一张校验码的图片导入到img里
	
		int width = 120;
		int height = 50;
		
		//创建画布
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// BufferedImage.TYPE_INT_RGB选择类型--红绿蓝
		//创建画笔 
		Graphics graphics = img.getGraphics();
		//设置画布的背景颜色
		graphics.setColor(Color.BLUE);//设置颜色
		graphics.fillRect(0, 0, width, height);//填充颜色
		//设置内容
		graphics.setColor(Color.WHITE);
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();//存放校验码的容器
		for (int i = 0; i < 4; i++) {
			graphics.setFont(new Font("宋体", Font.BOLD,ran.nextInt(10)+20));
			String num = String.valueOf(ran.nextInt(10));//0-9,生成一位校验码
			sb.append(num);
			graphics.drawString(num, 25*i+15, 20+ran.nextInt(15));//写入画布
		}
		
		//把校验码存起来
		//CheckCode.sb = sb;//多个客户端连接时，用到的校验码都是存放在同一个位置的，所有会出问题
		//正确存放校验码--Session
//		HttpSession session = request.getSession();
//		session.setAttribute("cc", sb);
		request.getSession().setAttribute("cc", sb);
		
		
		//设置干扰线
		graphics.setColor(Color.YELLOW);
		for (int i = 0; i < 15; i++) {
			graphics.drawLine(ran.nextInt(width), ran.nextInt(height), ran.nextInt(width), ran.nextInt(height));
		}
		
		//画出去 --- login.html(img)
		ImageIO.write(img, "jpg", response.getOutputStream());
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
