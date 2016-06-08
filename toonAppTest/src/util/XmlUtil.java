package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil{
	/** 
     * 格式化XML文件 
     * @param xml 
     * @return 
     * @throws DocumentException  
     * @throws IOException  
     */  
    public static String formatXml(String xml, String charset) throws Exception{              
                        
        Document document = DocumentHelper.parseText(xml);
        //创建输出格式  //OutputFormat类的createCompactFormat()方法或createPrettyPrint()方法格式化输出，默认采用createCompactFormat()方法，显示比较紧凑.
        OutputFormat format = OutputFormat.createPrettyPrint();       
        //制定输出xml的编码类型  
        format.setEncoding(charset);  
          
        StringWriter writer = new StringWriter();  
        //创建一个文件输出流  
        XMLWriter xmlwriter = new XMLWriter(writer, format);  
        //将格式化后的xml串写入到文件  
        xmlwriter.write(document);   
        String returnValue = writer.toString();  
        writer.close();    
           
        //返回编译后的字符串格式  
         return returnValue;  
    }  
    
    public static Document strToXML(String xmlStr){
    	Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	return document;
    }


    public static String getXMLStr(Document document){
    	try {
	      StringWriter sw = new StringWriter();
	      OutputFormat format = OutputFormat.createCompactFormat();
	      format.setEncoding("GBK");
	      XMLWriter xmlWriter = new XMLWriter(sw, format);
	      xmlWriter.write(document);
	      xmlWriter.close();
	      return sw.getBuffer().toString();
	    }
	    catch (Exception ex){
	      ex.printStackTrace();
	      return "";
	    }
    }

	 public static Element addRoot(String name)throws Exception{
	    Document document = DocumentHelper.createDocument();
	    Element root = document.addElement(name);
	    return root;
	 }

	 public static Element addElement(Element ele, String name, String text)throws Exception{
	    Element tmp = ele.addElement(name);
	    if ((text == null) || ("null".equals(text)))
	      tmp.setText("");
	    else {
	      tmp.setText(text);
	    }
	    return tmp;
	 }

  public static Element addElementAndCDATA(Element ele, String name, String text)throws Exception {
    Element tmp = ele.addElement(name);
    if ((text == null) || ("null".equals(text)))
      tmp.setText("");
    else {
      tmp.setText(text);
    }
    return tmp;
  }

  public static Element addElement(Element ele, String name, Object text) throws Exception {
    String str = String.valueOf(text);
    Element tmp = ele.addElement(name);
    tmp.setText("");
    tmp.setText(str);
    return tmp;
  }
  public static Element addIdNmValue(Element ele, String id, String chName, String value) throws Exception {
    Element retEle = addElement(ele, "col");
    retEle.addAttribute("chNm", chName);
    retEle.addAttribute("value", value);
    retEle.addAttribute("id", id);
    return retEle;
  }
  public static Element addIdValue(Element ele, String id, String value) throws Exception {
    Element retEle = addElement(ele, "col");
    retEle.addAttribute("value", value);
    retEle.addAttribute("id", id);
    return retEle;
  }

  public static Element addElement(Element ele, String name)
    throws Exception{
    Element tmp = ele.addElement(name);
    return tmp;
  }

  public static String getText(Element ele, String name)
    throws Exception{
    if (ele.element(name) == null) {
      return "";
    }
    return ele.element(name).getText();
  }

  public static Document readXML(String filePath){
    SAXReader reader = new SAXReader();
    Document doc = null;
    try {
      File file = new File(filePath);
      if (file.exists()) {
        doc = reader.read(new File(filePath));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return doc;
  }
  

  public static void main(String[] args) throws Exception  {
	  String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SLA Version=\"1.0\"><TxList><Tx>搜索</Tx><Tx>vuser_end_Transaction</Tx><Tx>Action_Transaction</Tx><Tx>vuser_init_Transaction</Tx></TxList><SLARules><TrackingPeriodType>Default</TrackingPeriodType><TrackingPeriodValue></TrackingPeriodValue><TrackingPeriodTimeScale></TrackingPeriodTimeScale></SLARules></SLA>";
	  String str_format = "";
	  Document doc = strToXML(xmlStr);
	  
	  Element em = doc.getRootElement();

	  
	  System.out.println(em.getName() + "---" + em.getStringValue());
	  
	  
	  
	  try {
		str_format = formatXml(xmlStr,"UTF-8");
	  } catch (Exception e) {
			e.printStackTrace();
	  }		    
	  
	System.out.println(str_format);   
  }
  	  
      
  
}
