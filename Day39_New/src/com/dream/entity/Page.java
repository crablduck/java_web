package com.dream.entity;

import java.util.ArrayList;

import com.dream.dto.StudentDto;

public class Page {
	
	private int currentPage;//当前页数
	private int pageSize;//每一页的数据条数
	private int totalPage;//总页数
	private String url;//地址
	private ArrayList list;//每一个页的数据
	
	public Page() {
	}

	public Page(int currentPage, int pageSize, int totalPage, String url) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.url = url;
	}



	public int getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getTotalPage() {
		return totalPage;
	}



	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public ArrayList getList() {
		return list;
	}



	public void setList(ArrayList list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", url="
				+ url + ", list=" + list + "]";
	}
	

}
