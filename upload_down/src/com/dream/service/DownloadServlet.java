package com.dream.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//解决文件中文问题及图片资源达到下载目的，而不是展示在页面上
		
		//获得要下载文件的路径
		String realPath = this.getServletContext().getRealPath("\\download\\渐变色.jpg");
		//获得文件名
		String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
		//设置编码格式
		fileName = URLEncoder.encode(fileName, "UTF-8");
		//设置以附件/文件的形式下载
		response.setHeader("Content-disposition", "attachment;fileName="+fileName);
		//读取源文件并写出去（浏览器）
		FileInputStream fis = new FileInputStream(realPath);
		ServletOutputStream out = response.getOutputStream();
		
		int len=0;
		byte[] b = new byte[1024];
		
		while((len=fis.read(b))!=-1){
			out.write(b, 0, len);
		}
		
		fis.close();
		out.close();
		
	}

}
