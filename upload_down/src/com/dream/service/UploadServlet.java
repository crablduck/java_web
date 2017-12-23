package com.dream.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.dream.entity.Student;

public class UploadServlet extends HttpServlet {
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
		
		m7(request, upload);
	}

	//上传 --- 优化获取完整信息（map + beanUtil）
	private void m7(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			/**
			 * fileItem.getFieldName() --- 获得组建名
			 * fileItem.getName() --- 获得文件名
			 */
			
			HashMap<String, String> map = new HashMap<String, String>();
			
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){//获得的是文本信息
					
					String key = fileItem.getFieldName();
					String value = map.get(key);
					
					if(value == null){
						map.put(key, fileItem.getString());
					}else{
						map.put(key, value+","+fileItem.getString());
					}
					
				}else{//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					
					//部署路径
					String realPathStr = this.getServletContext().getRealPath("\\upload");
					File realPath = new File(realPathStr);
					if(!realPath.exists()){
						realPath.mkdirs();
					}
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy\\MM\\dd\\HH\\mm\\ss");
					String datePath = sdf.format(new Date());
					
					File path = new File(realPath, datePath);
					if(!path.exists()){
						path.mkdirs();
					}
					
					FileOutputStream fos = new FileOutputStream(path + File.separator + fileItem.getName());
					IOUtils.copy(in, fos);//使用第三方框架拷贝文件
					fos.close();
					
					map.put("photoPath", path + File.separator + fileItem.getName());
				}
			}
			
			Student stu = new Student();
			BeanUtils.populate(stu, map);//将map通过反射的技术把值取出存到对象中
			System.out.println(stu);
			
			
		} catch (FileUploadException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
	}

	//上传 --- 获取完整信息（文本信息 、 二进制信息）
	private void m6(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			/**
			 * fileItem.getFieldName() --- 获得组建名
			 * fileItem.getName() --- 获得文件名
			 */
			
			Student stu = new Student();
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){//获得的是文本信息
					
					String value = fileItem.getString();
					if("username".equals(fileItem.getFieldName())){
						stu.setUsername(value);
					}else if("password".equals(fileItem.getFieldName())){
						stu.setPassword(value);
					}else if("sex".equals(fileItem.getFieldName())){
						stu.setSex(value);
					}else if("city".equals(fileItem.getFieldName())){
						stu.setCity(value);
					}else if("loves".equals(fileItem.getFieldName())){
						if(stu.getLoves() == null){
							stu.setLoves(value+",");
						}else{
							String loves = stu.getLoves();
							loves = loves + value + ",";
							stu.setLoves(loves);
						}
					}
					
				}else{//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					
					//部署路径
					String realPathStr = this.getServletContext().getRealPath("\\upload");
					File realPath = new File(realPathStr);
					if(!realPath.exists()){
						realPath.mkdirs();
					}
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy\\MM\\dd\\HH\\mm\\ss");
					String datePath = sdf.format(new Date());
					
					File path = new File(realPath, datePath);
					if(!path.exists()){
						path.mkdirs();
					}
					
					FileOutputStream fos = new FileOutputStream(path + File.separator + fileItem.getName());
					IOUtils.copy(in, fos);//使用第三方框架拷贝文件
					fos.close();
					
					stu.setPhotoPath(path + File.separator + fileItem.getName());
				}
			}
			
			System.out.println(stu);
			
		} catch (FileUploadException e) {
		}
	}

	//上传 --- 时间文件夹
	private void m5(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			for (FileItem fileItem : list) {
				if(!fileItem.isFormField()){//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					
					//部署路径
					String realPathStr = this.getServletContext().getRealPath("\\upload");
					File realPath = new File(realPathStr);
					if(!realPath.exists()){
						realPath.mkdirs();
					}
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy\\MM\\dd\\HH\\mm\\ss");
					String datePath = sdf.format(new Date());
					
					File path = new File(realPath, datePath);
					if(!path.exists()){
						path.mkdirs();
					}
					
					FileOutputStream fos = new FileOutputStream(path + File.separator + fileItem.getName());
					IOUtils.copy(in, fos);//使用第三方框架拷贝文件
					fos.close();
				}
			}
			
		} catch (FileUploadException e) {
		}
	}

	//上传 --- UUID(图片，缺点：改变了原有的文件名)
	private void m4(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			for (FileItem fileItem : list) {
				if(!fileItem.isFormField()){//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					
					//部署路径
					String realPathStr = this.getServletContext().getRealPath("\\upload");
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

	//上传 --- 部署路径+文件原名
	private void m3(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			for (FileItem fileItem : list) {
				if(!fileItem.isFormField()){//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					
					//部署路径
					String realPathStr = this.getServletContext().getRealPath("\\upload");
					File realPath = new File(realPathStr);
					if(!realPath.exists()){
						realPath.mkdirs();
					}
					
					FileOutputStream fos = new FileOutputStream(realPathStr + File.separator + fileItem.getName());
					IOUtils.copy(in, fos);//使用第三方框架拷贝文件
					fos.close();
				}
			}
			
		} catch (FileUploadException e) {
		}
	}
	
	//上传文件 ---（拷贝文件用的是第三方框架）
	private void m2(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			for (FileItem fileItem : list) {
				if(!fileItem.isFormField()){//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					FileOutputStream fos = new FileOutputStream("C://text.jpg");
					IOUtils.copy(in, fos);//使用第三方框架拷贝文件
					fos.close();
				}
			}
			
		} catch (FileUploadException e) {
		}
	}
	//最简单的上传
	private void m1(HttpServletRequest request, ServletFileUpload upload) throws IOException, FileNotFoundException {
		try {
			List<FileItem> list = upload.parseRequest(request);//此集合包含了客户端传来的文本信息和二进制信息
			
			for (FileItem fileItem : list) {
				if(!fileItem.isFormField()){//获得的是二进制信息
					
					InputStream in = fileItem.getInputStream();//输入流 -- 读取信息
					FileOutputStream fos = new FileOutputStream("C://text.jpg");
					
					byte[] b = new byte[1024];
					int len;
					while((len = in.read(b)) != -1){
						fos.write(b,0,len);
					}
					fos.close();
					in.close();
				}
			}
			
		} catch (FileUploadException e) {
		}
	}
}













