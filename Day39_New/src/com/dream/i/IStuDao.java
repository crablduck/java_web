package com.dream.i;

import java.util.ArrayList;

public interface IStuDao {//学生Dao的标准

	public int add();
	
	public int del();
	
	public ArrayList select();
	
	public int update(String ...info);
	
}
