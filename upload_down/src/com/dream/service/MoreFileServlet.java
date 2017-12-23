package com.dream.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class MoreFileServlet
 */
public class MoreFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//获得请求传递过来的信息
		DiskFileItemFactory factory = new DiskFileItemFactory();//创建ServletFileUpload的工厂
		ServletFileUpload upload = new ServletFileUpload(factory);//此对象可以获得请求里的信息

		
		m4(request, upload);
	}
	
	private void m4(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			for (FileItem fileItem : list) {
				if(!fileItem.isFormField()){//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					
					//部署路径
					String realPathStr = this.getServletContext().getRealPath("\\more");
					File realPath = new File(realPathStr);
					if(!realPath.exists()){
						realPath.mkdirs();
					}
					
					UUID uuid = UUID.randomUUID();//时间+系统信息
					String path = uuid+fileItem.getName();
					
					FileOutputStream fos = new FileOutputStream(realPathStr + File.separator + path);
					IOUtils.copy(in, fos);//使用第三方框架拷贝文件
					fos.close();
				}
			}
			
		} catch (FileUploadException e) {
		}
	}

}
