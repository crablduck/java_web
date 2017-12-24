package com.dream.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Test02 {

	public static void main(String[] args) throws Exception {
		
		//DOM解析
		
		//1.把XML文件导入到dom树中
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//DocumentBuilder的工厂
		DocumentBuilder builder = factory.newDocumentBuilder();//DocumentBuilder的创建者
		Document document = builder.parse(Test02.class.getClassLoader().getResourceAsStream("book.xml"));
		
		//2.解析
		NodeList tagName = document.getElementsByTagName("book");//获得book的列表
		
		for (int i = 0; i < tagName.getLength(); i++) {//依次获取出每一个book对象
			
			Element book = (Element) tagName.item(i);
			
			System.out.println(book.getElementsByTagName("bookid").item(0).getFirstChild().getNodeValue());
			System.out.println(book.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
			System.out.println(book.getElementsByTagName("price").item(0).getFirstChild().getNodeValue());
		}
	}
}
