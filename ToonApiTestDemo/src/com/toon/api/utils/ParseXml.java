package com.toon.api.utils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Package com.aoyou.test.util
 * @ClassName ParseXml
 * @Description xml文档处理模块
 */

public class ParseXml {
	
	String filePath;
	Document document;

	/**
	 *@param  filePath:待处理的xml文档路径
	 */
	public ParseXml(String filePath)
	{
		this.filePath=filePath;
		this.Load(filePath);
	}
	

	/*用来加载xml文件，并且产生一个document的对象*/
	private void Load(String filePath){
		File file=new File(filePath);
		if(file.exists()){
			SAXReader saxReader = new SAXReader();
			try{
				document=saxReader.read(file);
			}catch(DocumentException e){
				System.out.println("文件加载异常：" + filePath);
			}
		}else {
			System.out.println("文件不存在：" + filePath);
		}
	}
	
	/**
	 * 用xpath来得到一个元素节点对象
	 * @param elementPath是一个xpath路径，比如"/config/driver"
	 * @return 返回的是一个节点Element对象
	 */
	public Element getElementObject(String elementPath){
		return (Element) document.selectSingleNode(elementPath);
	}
	
	
	/**
	 * 用xpath来判断一个节点对象是否存在
	*/
	public boolean isExist(String elementPath){
		boolean flag=false;
		Element element = this.getElementObject(elementPath);
		if(element!=null)
			flag=true;
		return flag;
	}

	
	public String getElementText(String elementPath)
	{
		Element element=this.getElementObject(elementPath);
		if(element!=null)
			return element.getTextTrim();
		else 
			return null;
	}
	
	/*处理复杂同名多节点下的子节点,比如两个testUI下面是不同的子节点*/
	@SuppressWarnings("unchecked")
	public List<Element> getElementObjects(String elementPath){
		return document.selectNodes(elementPath);
	}
	
	public Map<String,String> getChildrenInfo(String elementPath,String key,String key2){
		List<Element> elements = this.getElementObjects(elementPath);
		Map<String,String> map = new LinkedHashMap<String,String>();

		for(Element e : elements){
			map.put(e.element(key).getTextTrim(), e.element(key2).getTextTrim());
		}
		return map;
	}
	
	public Map<String,String> getChildrenInfo(Element element){
		@SuppressWarnings("unchecked")
		List<Element> childrens = element.elements();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		for(Element e:childrens){
			map.put(e.getName(), e.getTextTrim());
		}
		return map;	
	}

	public Map<String,String> getChildrenInfo(String elementPath){

		@SuppressWarnings("unchecked")
		List<Element> elements = this.getElementObject(elementPath).elements();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		for(Element e:elements){
			map.put(e.getName(), e.getTextTrim());
		}
		return map;	
	}

}



