package com.dream.dom;

import java.io.File;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Test01 {

	public static void main(String[] args) throws Exception {

		/**
		 * DOM（文档对象模型）:将文档整个加载进内存，形成一颗DOM树（document对象），将文档的各个部分封装成为对象。
		 *	优点：因为在内存中形成dom树，可以对dom树进行增删改查
		 *	缺点：因为在内存中形成dom树，占用内存大，不适合大文件
		 *
		 * DOM学习流程:
		 * 1.利用DOM创建XML文件
		 * 2.利用DOM解析XML文件
		 */

		//1.创建Dom树
		//DocumentBuilder的工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//DocumentBuilder创建者
		DocumentBuilder builder = factory.newDocumentBuilder();
		//创建Dom树
		Document document = builder.newDocument();

		//创建根节点 --- <books></books>
		Element root = document.createElement("books");
		document.appendChild(root);

		//创建节点 --- book   <book></book>
		Element book = document.createElement("book");

		//创建节点 --- bookid
		Element bookid = document.createElement("bookid");//<bookid></bookid>
		Text bookid_text = document.createTextNode("01");//01
		bookid.appendChild(bookid_text);//<bookid>01</bookid>
		book.appendChild(bookid);

		//创建节点 --- name
		Element name = document.createElement("name");
		Text name_text = document.createTextNode("长物志");
		name.appendChild(name_text);
		book.appendChild(name);

		//创建节点 --- price
		Element price = document.createElement("price");
		Text price_text = document.createTextNode("888");
		price.appendChild(price_text);
		book.appendChild(price);

		//book加入到根节点
		root.appendChild(book);
		
		//创建结果树对象，并设置内容
		//结束树工厂
		TransformerFactory factory2 = TransformerFactory.newInstance();
		//创建结果树对象
		Transformer transformer = factory2.newTransformer();
		//设置内容
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");//设置编码
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");//设置编码
		
		//创建DOM资源对象
		DOMSource domSource = new  DOMSource(document);
		
		//创建流
		PrintWriter pw = new PrintWriter(new File("src\\book.xml"));
		//创建一个流结果对象
		StreamResult streamResult = new StreamResult(pw);
		
		//创建XML
		transformer.transform(domSource, streamResult);

	}
}
