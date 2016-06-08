package util;

import java.util.HashMap;
import java.util.Map;

public class SoapUtil {

	/**
	 * 使用httpclient 调用webservice的例子
	 * @since 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String url = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx" ;
		StringBuffer soapRequ = new StringBuffer();
		soapRequ.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">");
		soapRequ.append("<soapenv:Header/>");
		soapRequ.append("   <soapenv:Body>");
		soapRequ.append("       <web:getRegionCountry/>");
		soapRequ.append("   </soapenv:Body>");
		soapRequ.append("</soapenv:Envelope>");
		
		Map<String,String> header = new HashMap<String, String>();
		header.put("Content-Type","text/xml; charset=utf-8");    //注意不要用"application/soap+xml; charset=utf-8"
		
		String outmsg = util.HttpUtil.doPostStringReq(url, header, soapRequ.toString(), "utf-8");
		System.out.println(util.XmlUtil.formatXml(outmsg, "utf-8"));
				
	}
}
