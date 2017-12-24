package com.dream.db;

import java.util.ArrayList;
import java.util.HashMap;

import com.dream.entity.City;

public class DBCenter {

	//容器--存放省份和城市的映射关系
	public static HashMap<String, ArrayList<City>> map = new HashMap<>();
	
	//初始化数据
	static{
		
		ArrayList<City> list1 = new ArrayList<>();
		list1.add(new City("shenzhen", "深圳"));
		list1.add(new City("guangzhou", "广州"));
		list1.add(new City("dongguan", "东莞"));
		map.put("guangdong", list1);
		
		ArrayList<City> list2 = new ArrayList<>();
		list2.add(new City("changsha", "长沙"));
		list2.add(new City("zhuzhou", "株洲"));
		list2.add(new City("xiangtan", "湘潭"));
		map.put("hunan", list2);
		
		ArrayList<City> list3 = new ArrayList<>();
		list3.add(new City("wuhan", "武汉"));
		list3.add(new City("xiantao", "仙桃"));
		list3.add(new City("xianning", "咸宁"));
		map.put("hubei", list3);
		
	}
	
	
	
}
