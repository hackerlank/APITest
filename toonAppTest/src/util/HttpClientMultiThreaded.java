/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

/**
 * @author haoyuexun
 * @description ��httpClient4.3 ���߳�ִ��post get����
 */
package util;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.DateSwitch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


/**
 * ʹ�� httpclient4.4���ж��߳̽ӿڲ���.
 * @author haoyuexun
 */
public class HttpClientMultiThreaded {
	
	private  static int maxThreadNum =100;    //��������߳�������Ҫ��Ϊ������
	private static  Double totoalTimeSum = 0D;

	private static Long[] startTimeSumArray = new Long [maxThreadNum];
	private static Long[] endTimeSumArray = new Long [maxThreadNum] ;
	private static Long[] useTimeSumArray = new Long [maxThreadNum] ;
	private static int[] returnStatusArray = new int [maxThreadNum] ;
	
	static StringBuilder sb = new StringBuilder();
	static String[] returnMsg = new String[2];			// 0 ��Žӿڷ���   1 ���Log
	static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	
	/**
	 * post �ַ�����ʽ�Ķ��߳�
	 * @param httpclient
	 * @param testURL
	 * @param testPort
	 * @param threadNum
	 * @param postHeaders
	 * @param postBodyStr
	 * @throws Exception
	 */
    public static String[] httpClintPostString(String webSit,String testURL, int testPort, int threadNum, Map<String,String> postHeaders, String postBodyStr , String charset  ) throws Exception {
        // Create an HttpClient with the ThreadSafeClientConnManager.
        // This connection manager must be used if more than onhe thread will
        // be using the HttpClient.
//    	sb.delete(0, sb.length());		//������������sb
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss|SSS");			// �������ڸ�ʽ
	    
	    HttpHost testHost = new HttpHost(webSit, testPort);  // �������ĳ�ض���վָ�����������30
	    cm.setMaxTotal(maxThreadNum);	// ���ӳ�������������
	    cm.setDefaultMaxPerRoute(50);  // ÿ��·�ɵ�Ĭ�����������
	    cm.setMaxPerRoute(new HttpRoute(testHost), 50);  
	   
	    CloseableHttpClient httpclient = HttpClients.custom()
            .setConnectionManager(cm)
            .setConnectionManagerShared(true)		// ��Ҫ ���ӳ����Ӷ��case��Ҫhttpclient4.4���¹��ܣ�������case v4.3��java.lang.illegalstateexception connection pool shut down����ִ�еڶ���case��run
            .build();
        try {
            String reqString = postBodyStr;
            HttpPost httppost ;
     
            PostThread[] threads = new PostThread[threadNum];
            
            Long startTime = System.currentTimeMillis();
            String testStartDate = DateSwitch.CstUnixDateToStr(startTime.toString());
    		System.out.println("���Կ�ʼʱ��Ϊ��"+ testStartDate);
    		
            for (int k=0; k<threads.length; k++){
            	 httppost = new HttpPost(testURL); 

            	 for (String key : postHeaders.keySet()) {
            		   httppost.addHeader(key, postHeaders.get(key));
            	 }
                 StringEntity se = new StringEntity(reqString, charset);
                 httppost.setEntity(se);
               
                 threads[k] = new PostThread(httpclient, httppost, k+1 );
                 System.out.println("----thread---start=----"  + (k+1));
                 sb.append("----thread---start=----"  + (k+1) + "\n");
            }
            
            // start the threadsl
            for (int j = 0; j < threads.length; j++) {
                threads[j].start();
                System.out.println("---start" + (j+1));
                sb.append("---start" + (j+1) + "\n");
            }

            // join the threads
            for (int j = 0; j < threads.length; j++) {
                threads[j].join();
                System.out.println("---join--" + (j+1));
                sb.append("---join--" + (j+1) + "\n");
            }
            Long endTime = System.currentTimeMillis();	
			Long useTime = endTime - startTime;
			
			for (int i=0; i<threads.length; i++){
				Long usetimeSum = useTimeSumArray[i];
				totoalTimeSum = totoalTimeSum + usetimeSum;
				int returnStatus = returnStatusArray[i];
				String startTimeSum =  DateSwitch.CstUnixDateToStr(startTimeSumArray[i].toString());
				String endTimeSum = DateSwitch.CstUnixDateToStr(endTimeSumArray[i].toString());
				System.out.println("�߳� " + (i+1) + "-->���󷵻�״̬��Ϊ��" + returnStatus + "  ��ʼʱ��--" + startTimeSum  + "����ʱ��--" + endTimeSum +" ���õ�ʱ��Ϊ��" + usetimeSum + "����\n") ;
				sb.append("�߳� " + (i+1) +"-->���󷵻�״̬��Ϊ��" + returnStatus + "  ��ʼʱ��--" + startTimeSum  + "����ʱ��--" + endTimeSum +" ���õ�ʱ��Ϊ��" + usetimeSum + "����\n");
			}
			
			String testEndDate =  DateSwitch.CstUnixDateToStr(endTime.toString());
		
			System.out.println("post����" + threads.length + " �ο�ʼʱ��Ϊ��" + testStartDate +"����ʱ��Ϊ��" +  testEndDate + " �̹߳���ʱ��Ϊ��" + useTime + "����\n");
			System.out.println("post����" + threads.length +" �Ρ��ۻ�������ʱ��Ϊ��" + totoalTimeSum + "����,�ӿ�ƽ����Ӧʱ��Ϊ��" + Double.toString(totoalTimeSum/threads.length) + "����\n");	
			sb.append("post����" + threads.length + "���ο�ʼʱ��Ϊ��" + testStartDate +"����ʱ��Ϊ��" +  testEndDate + " ���̹߳���ʱ��Ϊ��" + useTime + "����\n");
			sb.append("post����" + threads.length +"���Ρ��ۻ�������ʱ��Ϊ��" + totoalTimeSum + "����,�ӿ�ƽ����Ӧʱ��Ϊ��" + Double.toString(totoalTimeSum/threads.length) + "����\n");
        } finally {
        	
            httpclient.close();
            
        }
    	System.out.println("*************************************�߳�ִ�н���****************************************");
    	sb.append("*************************************�߳�ִ�н���****************************************\n");
    	
    	returnMsg[1] = sb.toString();		//����ӡ����浽����1
    	return returnMsg;
	
    }
    
    public static String[] httpClintPostForm(String webSit,String testURL, int testPort, int threadNum, Map<String,String> postHeaders, Map<String,String> params , String charset  ) throws Exception {
        // Create an HttpClient with the ThreadSafeClientConnManager.
        // This connection manager must be used if more than onhe thread will
        // be using the HttpClient.
//    	sb.delete(0, sb.length());		//������������sb
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss|SSS");			// �������ڸ�ʽ
	    
	    HttpHost testHost = new HttpHost(webSit, testPort);  // �������ĳ�ض���վָ�����������30
	    cm.setMaxTotal(maxThreadNum);	// ���ӳ�������������
	    cm.setDefaultMaxPerRoute(50);  // ÿ��·�ɵ�Ĭ�����������
	    cm.setMaxPerRoute(new HttpRoute(testHost), 50);  
	   
	    CloseableHttpClient httpclient = HttpClients.custom()
            .setConnectionManager(cm)
            .setConnectionManagerShared(true)		// ��Ҫ ���ӳ����Ӷ��case��Ҫhttpclient4.4���¹��ܣ�������case v4.3��java.lang.illegalstateexception connection pool shut down����ִ�еڶ���case��run
            .build();
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
            HttpPost httppost ;
     
            PostThread[] threads = new PostThread[threadNum];
            
            Long startTime = System.currentTimeMillis();
            String testStartDate = DateSwitch.CstUnixDateToStr(startTime.toString());
    		System.out.println("���Կ�ʼʱ��Ϊ��"+ testStartDate);
    		
            for (int k=0; k<threads.length; k++){
            	 httppost = new HttpPost(testURL); 

            	 for (String key : postHeaders.keySet()) {
            		   httppost.addHeader(key, postHeaders.get(key));
            	 }
            	 
            	   if(pairs != null && pairs.size() > 0){
            		   httppost.setEntity(new UrlEncodedFormEntity(pairs,charset));
                   }

                 
                 threads[k] = new PostThread(httpclient, httppost, k+1 );
                 System.out.println("----thread---start=----"  + (k+1));
                 sb.append("----thread---start=----"  + (k+1) + "\n");
            }
            
            // start the threadsl
            for (int j = 0; j < threads.length; j++) {
                threads[j].start();
                System.out.println("---start" + (j+1));
                sb.append("---start" + (j+1) + "\n");
            }

            // join the threads
            for (int j = 0; j < threads.length; j++) {
                threads[j].join();
                System.out.println("---join--" + (j+1));
                sb.append("---join--" + (j+1) + "\n");
            }
            Long endTime = System.currentTimeMillis();	
			Long useTime = endTime - startTime;
			
			for (int i=0; i<threads.length; i++){
				Long usetimeSum = useTimeSumArray[i];
				totoalTimeSum = totoalTimeSum + usetimeSum;
				int returnStatus = returnStatusArray[i];
				String startTimeSum =  DateSwitch.CstUnixDateToStr(startTimeSumArray[i].toString());
				String endTimeSum = DateSwitch.CstUnixDateToStr(endTimeSumArray[i].toString());
				System.out.println("�߳� " + (i+1) + "-->���󷵻�״̬��Ϊ��" + returnStatus + "  ��ʼʱ��--" + startTimeSum  + "����ʱ��--" + endTimeSum +" ���õ�ʱ��Ϊ��" + usetimeSum + "����\n") ;
				sb.append("�߳� " + (i+1) +"-->���󷵻�״̬��Ϊ��" + returnStatus + "  ��ʼʱ��--" + startTimeSum  + "����ʱ��--" + endTimeSum +" ���õ�ʱ��Ϊ��" + usetimeSum + "����\n");
			}
			
			String testEndDate =  DateSwitch.CstUnixDateToStr(endTime.toString());
		
			System.out.println("post����" + threads.length + " �ο�ʼʱ��Ϊ��" + testStartDate +"����ʱ��Ϊ��" +  testEndDate + " �̹߳���ʱ��Ϊ��" + useTime + "����\n");
			System.out.println("post����" + threads.length +" �Ρ��ۻ�������ʱ��Ϊ��" + totoalTimeSum + "����,�ӿ�ƽ����Ӧʱ��Ϊ��" + Double.toString(totoalTimeSum/threads.length) + "����\n");	
			sb.append("post����" + threads.length + "���ο�ʼʱ��Ϊ��" + testStartDate +"����ʱ��Ϊ��" +  testEndDate + " ���̹߳���ʱ��Ϊ��" + useTime + "����\n");
			sb.append("post����" + threads.length +"���Ρ��ۻ�������ʱ��Ϊ��" + totoalTimeSum + "����,�ӿ�ƽ����Ӧʱ��Ϊ��" + Double.toString(totoalTimeSum/threads.length) + "����\n");
        } finally {
        	
            httpclient.close();
            
        }
    	System.out.println("*************************************�߳�ִ�н���****************************************");
    	sb.append("*************************************�߳�ִ�н���****************************************\n");
    	
    	returnMsg[1] = sb.toString();		//����ӡ����浽����1
    	return returnMsg;
	
    }
    
    public static String[] httpClintMultiPostFormFile(String webSit,String testURL, int testPort, int threadNum, Map<String,String> postHeaders, Map<String,String> params, String fileName, String filePath  ) throws Exception {
    	File img_file;
    	FileBody bin = null;
    	if(! filePath.equalsIgnoreCase("")){
    		img_file = new File(filePath);
        	bin = new FileBody(img_file);
    	}
    	
    	MultipartEntityBuilder mb = MultipartEntityBuilder.create();
    	sb.delete(0, sb.length());		//������������sb
   
	    HttpHost testHost = new HttpHost(webSit, testPort);  // �������ĳ�ض���վָ�����������30
	    cm.setMaxTotal(100);	// ���ӳ�������������
	    cm.setDefaultMaxPerRoute(30);  // ÿ��·�ɵ�Ĭ�����������
	    cm.setMaxPerRoute(new HttpRoute(testHost), 30);  
	   
	    CloseableHttpClient httpclient = HttpClients.custom()
            .setConnectionManager(cm)
            .build();
        try {
        	if(! filePath.equalsIgnoreCase("")){
        		mb.addPart(fileName, bin);
        	}
        	
        	for(Map.Entry<String,String> entry : params.entrySet()){
                String value = entry.getValue();
                if(value != null){
                	mb.addTextBody(entry.getKey(),value);
                }
            }
            
            HttpEntity reqEntity = mb.build();
            HttpPost httppost ;
            
            PostThread[] threads = new PostThread[threadNum];
            
            Long startTime = System.currentTimeMillis();
            String testStartDate = DateSwitch.CstUnixDateToStr(startTime.toString());
    		System.out.println("���Կ�ʼʱ��Ϊ��"+ testStartDate);
    		
            for (int k=0; k<threads.length; k++){
            	 httppost = new HttpPost(testURL); 
            	 for(Map.Entry<String,String> entry : postHeaders.entrySet()){
	              	String key = entry.getKey();
	              	String value = entry.getValue();
	              	httppost.addHeader(key, value);   
            	 }
                 httppost.setEntity(reqEntity);
               
                 threads[k] = new PostThread(httpclient, httppost, k+1 );
                 System.out.println("----thread---start=----"  + (k+1));
                 sb.append("----thread---start=----"  + (k+1) + "\n");
            }
            
            // start the threadsl
            for (int j = 0; j < threads.length; j++) {
                threads[j].start();
                System.out.println("---start" + (j+1));
                sb.append("---start" + (j+1) + "\n");
            }

            // join the threads
            for (int j = 0; j < threads.length; j++) {
                threads[j].join();

                System.out.println("---join--" + (j+1));
//                sb.append("---join--" + (j+1) + "\n");
            }
            Long endTime = System.currentTimeMillis();	
			Long useTime = endTime - startTime;
			
			for (int i=0; i<threads.length; i++){
				Long usetimeSum = useTimeSumArray[i];
				totoalTimeSum = totoalTimeSum + usetimeSum;
				int returnStatus = returnStatusArray[i];
				System.out.println("�߳� " + (i+1) + "-->���󷵻�״̬��Ϊ��" + returnStatus + "  ��ʼʱ��--" + DateSwitch.CstUnixDateToStr(startTimeSumArray[i].toString())  + "����ʱ��--" + DateSwitch.CstUnixDateToStr(endTimeSumArray[i].toString()) +" ���õ�ʱ��Ϊ��" + usetimeSum + "����\n") ;
				sb.append("�߳� " + (i+1) +"-->���󷵻�״̬��Ϊ��" + returnStatus + "  ��ʼʱ��--" + DateSwitch.CstUnixDateToStr(startTimeSumArray[i].toString())  + "����ʱ��--" + DateSwitch.CstUnixDateToStr(endTimeSumArray[i].toString()) +" ���õ�ʱ��Ϊ��" + usetimeSum + "����\n");
			}
			
			String testEndDate =  DateSwitch.CstUnixDateToStr(endTime.toString());
		
			System.out.println("post����" + threads.length + " �ο�ʼʱ��Ϊ��" + testStartDate +"����ʱ��Ϊ��" +  testEndDate + " �̹߳���ʱ��Ϊ��" + useTime + "����\n");
			System.out.println("post����" + threads.length +" �Ρ��ۻ�������ʱ��Ϊ��" + totoalTimeSum + "����,�ӿ�ƽ����Ӧʱ��Ϊ��" + Double.toString(totoalTimeSum/threads.length) + "����\n");	
			sb.append("post����" + threads.length + "���ο�ʼʱ��Ϊ��" + testStartDate +"����ʱ��Ϊ��" +  testEndDate + " ���̹߳���ʱ��Ϊ��" + useTime + "����\n");
			sb.append("post����" + threads.length +"���Ρ��ۻ�������ʱ��Ϊ��" + totoalTimeSum + "����,�ӿ�ƽ����Ӧʱ��Ϊ��" + Double.toString(totoalTimeSum/threads.length) + "����\n");
        } finally {
            httpclient.close();
        }
    	System.out.println("*************************************�߳�ִ�н���****************************************");
    	sb.append("*************************************�߳�ִ�н���****************************************\n");
    	
    	returnMsg[1] = sb.toString();		//����ӡ����浽����1
    	return returnMsg;
	
    }
    
  
    
    /**
     * A thread that performs a GET.
     */
    static class GetThread extends Thread {

        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpget;
        private final int id;

        public GetThread(CloseableHttpClient httpClient, HttpGet httpget, int id) {
            this.httpClient = httpClient;
            this.context = new BasicHttpContext();
            this.httpget = httpget;
            this.id = id;
        }
        
        /**
         * Executes the GetMethod and prints some status information.
         */
        @Override
        public void run() {
            try {
                System.out.println(id + " - about to get something from " + httpget.getURI());
                CloseableHttpResponse response = httpClient.execute(httpget, context);
                try {
                    System.out.println(id + " - get executed");
                    // get the response body as an array of bytes
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        byte[] bytes = EntityUtils.toByteArray(entity);
                        System.out.println(id + " - " + bytes.length + " bytes read");
                    }
                } finally {
                    response.close();
                }
            } catch (Exception e) {
            	System.out.println(id + " - error: " + e);
            }
        }

    }
    
    static class PostThread extends Thread {
 
        private final CloseableHttpClient httpClient;
//        private final HttpContext context;
        private final int id;
        private final HttpPost httppost;

        public PostThread(CloseableHttpClient httpClient, HttpPost httppost, int id) {
            this.httpClient = httpClient;
//            this.context = new BasicHttpContext();
            this.httppost = httppost;
            this.id = id;
        }
        
        
        @Override
        public void run() {
            try {
            	System.out.println(id + " - about to post something from " + httppost.getRequestLine().getUri());
            	sb.append(id + " - about to post something from " + httppost.getRequestLine().getUri() + "\n");
                long startTimeSum = System.currentTimeMillis();
                startTimeSumArray[id-1] = startTimeSum;             
                CloseableHttpResponse response = httpClient.execute(httppost);
                
                try {
                	 System.out.println("===========================================================");
                	 System.out.println("�߳�->" + id + " - post executed");

                	 HttpEntity entity = response.getEntity();
                	 System.out.println("�߳�->" + id +" executing request " + httppost.getRequestLine().getUri()); 
                	 int returnStatus = response.getStatusLine().getStatusCode();
                	 returnStatusArray[id-1] = returnStatus;
                	 String content = EntityUtils.toString(entity);
                	 returnMsg[0] = content;   //���ӿڷ��ش浽����0
                	 System.out.println("�ӿڷ��أ�id " + id + "---" + content);
                	 sb.append("�ӿڷ��أ�id " + id + "---" + content + "\n");
                	
                	 long endTimeSum = System.currentTimeMillis();
               sb.append("----debug:" + endTimeSum);
                	 endTimeSumArray[id-1] = endTimeSum;
                	 
                	 long useTimeSum = endTimeSum - startTimeSum;
                	 useTimeSumArray[id-1] = useTimeSum;   
//                	 System.out.println("�ӿ��߳�" + id + ":����ʱ��Ϊ��" + useTimeSum + "����");
                	 System.out.println("===========================================================");
//                	 sb.append("�ӿ��߳�" + id + ":����ʱ��Ϊ��" + useTimeSum + "����\n");
                	 sb.append("===========================================================\n");
                } finally {
                    response.close();
//                    httpClient.close();
//                    cm.close();
//                    cm.shutdown();
                    totoalTimeSum = 0D;
                }
            } catch (Exception e) {
            	System.out.println(id + " - error: " + e);
           }
        }
    }
}
