package com.dream.sax;

import java.io.FileInputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Test01 {

	public static void main(String[] args) throws Exception{
		
		//SAX解析
		
		//sax解析器的工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//sax解析器
		SAXParser parser = factory.newSAXParser();
		
		//处理器
		MyHandler myHandler = new MyHandler();
		
		//解析文件
		parser.parse(new FileInputStream("phone.xml"), myHandler);
		
		ArrayList<Phone> list = myHandler.getList();
		for (Phone phone : list) {
			System.out.println(phone);
		}
		
		
	}
}

//处理器必须用自己的，因为每个xml内的结构是不一样的
class MyHandler extends DefaultHandler{
	
	private ArrayList<Phone> list = null;
	private Phone phone = null;
	private String flag;

	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<>();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("解析文档结束");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		flag = qName;
		
		if("phone".equals(qName)){
			phone = new Phone();
			
			int attrLength = attributes.getLength();//属性个数
			for (int i = 0; i < attrLength; i++) {
				String name = attributes.getQName(i);
				String value = attributes.getValue(i);
				if("id".equals(name)){
					phone.setId(value);
				}
			}
			
			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String info = new String(ch,start,length).trim();
		if(info.length() != 0){
			
			if(flag.equals("type")){
				phone.setType(info);
			}else if(flag.equals("price")){
				phone.setPrice(info);
			}else if(flag.equals("store")){
				phone.setStore(info);
			}
			
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if("phone".equals(qName)){
			list.add(phone);
		}
	}

	
	
	public ArrayList<Phone> getList(){
		return list;
	}
	
	
}


