package com.dream.utils;

import java.util.ArrayList;

import com.dream.dto.StudentDto;
import com.dream.entity.Student;

public class DtoUtils {

	public static ArrayList<StudentDto> stulist4Dto(ArrayList<Student> stulist){
		ArrayList<StudentDto> dtolist = new ArrayList<>();
		
		for (Student stu : stulist) {
			StudentDto stuDto = stu4Dto(stu);
			dtolist.add(stuDto);
		}
		return dtolist;
	}
	
	public static StudentDto stu4Dto(Student stu){
		
		StudentDto dto = new StudentDto();
		StringBuffer sb = new StringBuffer();
		dto.setStu(stu);
		String[] loves = stu.getLoves();
		for (String str : loves) {
			sb.append(str);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		dto.setLoveStr(sb.toString());
		return dto;
	}
	
	
}
