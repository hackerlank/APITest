package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 

import com.toon.api.test.dao.ToonAppDaoService;
 
/**
 * ���� httpclient 4.3�汾�� http������
 * 
 *	@author haoyuexun
 */
public class HttpUtil {
	
	private static final Log log = LogFactory.getLog(HttpUtil.class);
    private static final CloseableHttpClient httpClient;
    private static final String CHARSET = "UTF-8";
    private static final int timeout = 5000;
//    private static  final BasicCookieStore cookieStore  ;
    
	
	
    static {	
        //��������ĳ�ʱ����  
        RequestConfig requestConfig = RequestConfig.custom()    
                .setConnectionRequestTimeout(timeout)  
                .setConnectTimeout(timeout)    
                .setSocketTimeout(timeout).build();    
       
      /*   // cookieʹ��û�гɹ� ֱ����header ���
        cookieStore = new BasicCookieStore();
    	BasicClientCookie cookie2 = new BasicClientCookie("PHPSESSID","dt9ije6vvdfvef7q20n9lch3h7");
    	BasicClientCookie cookie = new BasicClientCookie("token","b82c1388eb25b023b38f85eec218cee94079942457");
    	cookie.setDomain("daojia.com.cn");
    	cookie2.setDomain("daojia.com.cn");
    	cookieStore.addCookie(cookie);
    	cookieStore.addCookie(cookie2);

        httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(requestConfig).build();
     
      */
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }
 
 
    /**
     * HTTP Get ��ȡ����
     * @param url  �����url��ַ ?֮ǰ�ĵ�ַ
     * @param params ����Ĳ���
     * @param charset    �����ʽ
     * @return   ��������
     */
    public static String doGetForm(String url, Map<String,String> params, String charset){
    	String result = "";
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
      
    }
    /**
     * get һ�����õ�url
     * @param url  http://xxx.com?xx=a&xx=c
     * @param params
     * @param charset
     * @return
     */
    public static String doGetStrReq(String url,String charset){
    	String result = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
      
    }
    /**
     * post form������ʽ
     * @param url
     * @param postHeader
     * @param params
     * @param charset
     * @return
     */
    public static String doPostFormReq(String url,Map<String, String> postHeader, Map<String,String> params,String charset){
    	String result = "";
        try {
        	 
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
             	String key = entry.getKey();
             	String value = entry.getValue();
             	httpPost.addHeader(key, value);
             }
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
//                throw new RuntimeException("HttpClient,error status code :" + statusCode); // toon�ܶ������Ľӿڱ�500...
               log.info("HttpClient,error status code :" + statusCode);     
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * post �ϴ��ļ�
     * @param url
     * @param postHeader
     * @param params
     * @param charset
     * @return 
     */
    public static String doPostFormFile(String url,Map<String, String> postHeader, Map<String,String> params, String fileName, String filePath, String charset){
    	String result = "";
    	File img_file = new File(filePath);
    	FileBody bin = new FileBody(img_file);
    	MultipartEntityBuilder mb = MultipartEntityBuilder.create();
        try {

            if(params != null && !params.isEmpty()){
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                    	mb.addTextBody(entry.getKey(),value);
                    }
                }
            }
            mb.addPart(fileName, bin);
            HttpEntity reqEntity = mb.build();
            HttpPost httpPost = new HttpPost(url);
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
             	String key = entry.getKey();
             	String value = entry.getValue();
             	httpPost.addHeader(key, value);
             }
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * post ����Ϊstr ��ʽ ��header
     * @param url
     * @param postHeader
     * @param strRequest
     * @param charset
     * @return
     */
    public static String doPostStringReq(String url, Map<String, String> postHeader, String strRequest, String charset){
    	String result = "";
        try {
        	HttpPost httpPost = new HttpPost(url);

            StringEntity strEntity = new StringEntity(strRequest, charset);
        
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
            	String key = entry.getKey();
            	String value = entry.getValue();
            	httpPost.addHeader(key, value);
            }
            httpPost.setEntity(strEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * post ����Ϊstr û��header
     * @param url
     * @param strRequest
     * @param charset
     * @return
     */
    
    
    public static String doPostStringReq(String url, String strRequest, String charset){
    	String result = "";
        try {
        	
			
			
        	HttpPost httpPost = new HttpPost(url);
            StringEntity strEntity = new StringEntity(strRequest, charset);
          
            httpPost.setEntity(strEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
       
}