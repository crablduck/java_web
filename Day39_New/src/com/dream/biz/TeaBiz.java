package com.dream.biz;

import java.util.ArrayList;

import com.dream.dao.TeaDao;
import com.dream.dto.StudentDto;
import com.dream.entity.Student;
import com.dream.entity.Teacher;
import com.dream.utils.DtoUtils;

public class TeaBiz {
	
	TeaDao teaDao = new TeaDao();

	public ArrayList<Teacher> getTeaList(int currentPage, int pageSize) {

		//从dao层获取到的学生集合
		int index = (currentPage-1)*pageSize;//偏移量算法
		ArrayList<Teacher> teaList = teaDao.getTeaList(index,pageSize);
		return teaList;
	}

	public int getTotalPage(int pageSize) {

		int count = teaDao.getAllCount();
		//整除直接返回页数，否则+1
		int totalPage = (count%pageSize == 0)?count/pageSize:count/pageSize+1;

		return totalPage;
	}


}
