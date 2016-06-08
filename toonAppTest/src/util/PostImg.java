package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.entity.HttpEntityWrapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;

/**
 * 
 *  用httpclient4.2 版本 上传图片
 */
public class PostImg {
 
    public PostImg() throws Exception {
    }
    
    public static String postImg(String urlString, String params, String token, String sign, String time, String clientType, String encode, String app_version,String uid, String interfaceType) throws Exception{
    
    	String appVersion = "";
		String traceInfo = "";
		String content = "";
		if(app_version.equalsIgnoreCase("")){
			appVersion = "7.0";
		}else {
			appVersion = app_version;
		}
		
		System.out.println("上传图片接口========" + appVersion);
		 if(clientType.equalsIgnoreCase("android")){
			   traceInfo = "userid=" + uid +";versionname=" + appVersion + ";versioncode=1;sim=13581535754;macaddress=8c:be:be:f0:85:27;buildversion=4.3;osversion=18;model=HUAWEI+B199;appname=groupbuy;clientname=android;channelid=10062;cityid=2419;loc=112.426697,34.599468;network=wifi;clientid=863637027549372;imei=863637027549372";

		   }else if(clientType.equalsIgnoreCase("iphone")) {
			   traceInfo = "userid=" + uid +";versionname=" + appVersion + ";versioncode=1;sim=13581535754;macaddress=8c:be:be:f0:85:27;buildversion=4.3;osversion=18;model=Iphone 6S;appname=groupbuy;clientname=iphone;channelid=10062;cityid=2419;idfa=A3D3BB44-A5CE;openudid=c4387644dd9e8535dc5abad1023097e9eb58864e;loc=112.426697,34.599468;network=wifi;clientid=863637027549372;imei=863637027549372";
		   }else{

			   traceInfo = "userid=" + uid +";versionname=" + appVersion + ";versioncode=1;sim=18981535700;macaddress=8c:be:be:f0:85:27;buildversion=4.3;osversion=18;model=HUAWEI+B199;appname=groupbuy;clientname=android;channelid=10062;cityid=2149;loc=112.426697,34.599468;network=wifi;clientid=863637027549372;imei=863637027549372";
		   }
		 
		 System.out.println("traceInfo--" + traceInfo);
    	DefaultHttpClient httpclient = new DefaultHttpClient();

  
        try {
            HttpPost httppost = new HttpPost(urlString);
      
            httppost.addHeader("ContentType","multipart/form-data");
            StringBody sb_token = new StringBody(token);
            StringBody sb_sign = new StringBody(sign);
            StringBody sb_params = new StringBody(params);
            StringBody sb_time = new StringBody(time);
            StringBody sb_traceinfo = new StringBody(traceInfo);
            
            String imgFile =  System.getProperty("user.dir") + "/" + "123.jpg" ;
            File file = new File(imgFile);
            FileBody bin = new FileBody(file);
            MultipartEntity reqEntity = new MultipartEntity();
            String mu = reqEntity.getContentType().toString().substring(14);

            System.out.println("------" + mu);
            reqEntity.addPart("img_file", bin);
            reqEntity.addPart("token", sb_token);
            reqEntity.addPart("sign", sb_sign);
            reqEntity.addPart("params", sb_params);
            reqEntity.addPart("time", sb_time);
            reqEntity.addPart("traceinfo", sb_traceinfo);
           
            httppost.setEntity(reqEntity);

         // ===================================================================   
            System.out.println("executing request " + httppost.getRequestLine());
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
 
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            
            content = EntityUtils.toString(resEntity);
        	System.out.println("接口返回：" + content);
		 
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            EntityUtils.consume(resEntity);
        } finally {
            try { 
            	httpclient.getConnectionManager().shutdown(); 
            } 
            catch (Exception ignore) {
            	
            }
            
        }
       return content;
    }
    
    public static void main(String[] args) {
    	try {
			PostImg pi = new PostImg();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
     
}

