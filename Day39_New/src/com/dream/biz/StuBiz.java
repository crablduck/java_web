package com.dream.biz;

import java.util.ArrayList;

import com.dream.dao.StuDao;
import com.dream.dto.StudentDto;
import com.dream.entity.Student;
import com.dream.utils.DtoUtils;

public class StuBiz {

	private StuDao stuDao = new StuDao();

	//获取学生Dto集合的
	public ArrayList<StudentDto> getStuList() {

		//从dao层获取到的学生集合
		ArrayList<Student> stuList = stuDao.getStuList();
		//把学生集合生成对应的学生Dto集合
		ArrayList<StudentDto> stuDto = DtoUtils.stulist4Dto(stuList);

		return stuDto;
	}

	public ArrayList<StudentDto> getStuList(int currentPage, int pageSize) {

		//从dao层获取到的学生集合
		
		//第一页偏移量：0
		//第二页偏移量：5
		//第三页偏移量：10
		//第四页偏移量：15
		int index = (currentPage-1)*pageSize;//偏移量算法
		ArrayList<Student> stuList = stuDao.getStuList(index,pageSize);
		//把学生集合生成对应的学生Dto集合
		ArrayList<StudentDto> stuDto = DtoUtils.stulist4Dto(stuList);

		return stuDto;
	}


	public boolean isRegister(String username) {

		int num = stuDao.isRegister(username);
		if(num==1){
			return false;
		}
		return true;
	}

	public boolean add(String username, String password, String sex, String city, String[] loves) {

		StudentDto stuDto = DtoUtils.stu4Dto(new Student(username, password, sex, city, loves, 1));
		int num = stuDao.add(stuDto);
		if(num != 0){
			return true;
		}
		return false;
	}

	public boolean delete(String id) {

		int num = stuDao.delete(Integer.parseInt(id));

		if(num != 0){
			return true;
		}
		return false;
	}

	public StudentDto getStuById(String id) {

		Student stu =  stuDao.getStubyId(Integer.parseInt(id));
		StudentDto stuDto = DtoUtils.stu4Dto(stu);

		return stuDto;
	}

	public boolean update(String id, String sex, String city, String[] loves) {

		StudentDto stuDto = DtoUtils.stu4Dto(new Student(Integer.parseInt(id), sex, city, loves));
		int num = stuDao.update(stuDto);
		if(num != 0){
			return true;
		}
		return false;
	}

	public int getTotalPage(int pageSize) {
		
		int count = stuDao.getAllCount();
		//整除直接返回页数，否则+1
		int totalPage = (count%pageSize == 0)?count/pageSize:count/pageSize+1;
		
		return totalPage;
	}

	public Student getStuByUsernamePassword(String username, String password) {
		
		Student stu = stuDao.getStuByUsernamePassword(username, password);
		
		return stu;
	}



}







