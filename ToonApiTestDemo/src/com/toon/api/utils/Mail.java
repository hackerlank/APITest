package com.toon.api.utils;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
	
	 	private MimeMessage mimeMsg; //MIME邮件对象   
	 	private Session session; //邮件会话对象   
	 	private Properties props; //系统属性   
	 	//smtp认证用户名和密码   
	 	private String username;   
	 	private String password;   
	 	private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象   
 
	    /** 
	     * Constructor 
	     * @param smtp 邮件发送服务器 
	     */  
	  	public Mail(String smtp){   
	      setSmtpHost(smtp);   
	      createMimeMessage();   
	  }   
	  
	    /** 
	     * 设置邮件发送服务器 
	     * @param hostName String  
	     */  
	  	public void setSmtpHost(String hostName) {   
	  		System.out.println("设置系统属性：mail.smtp.host = "+hostName);   
	        if(props == null)  
	            props = System.getProperties(); //获得系统属性对象    
	        props.put("mail.smtp.host",hostName); //设置SMTP主机   
	    }   
	  
	  
	    /** 
	     * 创建MIME邮件对象   
	     * @return 
	     */  
	    public boolean createMimeMessage()   
	    {   
	        try {   
	            System.out.println("准备获取邮件会话对象！");   
	            session = Session.getDefaultInstance(props,null); //获得邮件会话对象   
	        }   
	        catch(Exception e){   
	            System.err.println("获取邮件会话对象时发生错误！"+e);   
	            return false;   
	        }   
	      
	        System.out.println("准备创建MIME邮件对象！");   
	        try {   
	            mimeMsg = new MimeMessage(session); //创建MIME邮件对象   
	            mp = new MimeMultipart("related");   
	          
	            return true;   
	        } catch(Exception e){   
	            System.err.println("创建MIME邮件对象失败！"+e);   
	            return false;   
	        }   
	    }     
	      
	    /** 
	     * 设置SMTP是否需要验证 
	     * @param need 
	     */  
	    public void setNeedAuth(boolean need) {   
	    	System.out.println("设置smtp身份认证：mail.smtp.auth = "+need);   
	        if(props == null) props = System.getProperties();   
	        if(need){   
	            props.put("mail.smtp.auth","true");   
	        }else{   
	            props.put("mail.smtp.auth","false");   
	        }   
	    }   
	  
	    /** 
	     * 设置用户名和密码 
	     * @param name 
	     * @param pass 
	     */  
	    public void setNamePass(String name,String pass) {   
	        username = name;   
	        password = pass;   
	    }   
	  
	    /** 
	     * 设置邮件主题 
	     * @param mailSubject 
	     * @return 
	     */  
	    public boolean setSubject(String mailSubject) {   
	    	System.out.println("设置邮件主题！");   
	        try{   
	            mimeMsg.setSubject(mailSubject);   
	            return true;   
	        }   
	        catch(Exception e) {   
	            System.err.println("设置邮件主题发生错误！");   
	            return false;   
	        }   
	    }  
	      
	    /**  
	     * 设置邮件正文 
	     * @param mailBody String  
	     */   
	    public boolean setBody(String mailBody) {   
	        try{   
	        	MimeBodyPart textBody = new MimeBodyPart();                
	            textBody.setContent(mailBody, "text/html;charset=gb2312");           
	            mp.addBodyPart(textBody);
	            return true;   
	        } catch(Exception e){   
	        System.err.println("设置邮件正文时发生错误！"+e);   
	        return false;   
	        }   
	    }   
	    /**  
	     * 添加附件 
	     * @param filename String  
	     */   
	    public boolean addFileAffix(String filename) {   
	      
	    	System.out.println("增加邮件附件："+filename);   
	        try{   
	            BodyPart bp = new MimeBodyPart();   
	            FileDataSource fileds = new FileDataSource(filename);   
	            bp.setDataHandler(new DataHandler(fileds));   
	            bp.setFileName(fileds.getName());   
	            mp.addBodyPart(bp);   
	              
	            return true;   
	        } catch(Exception e){   
	            System.err.println("增加邮件附件："+filename+"发生错误！"+e);   
	            return false;   
	        }   
	    }   
	      
	    /**  
	     * 设置发信人 
	     * @param from String  
	     */   
	    public boolean setFrom(String from) {   
	    	System.out.println("设置发信人！");   
	        try{   
	            mimeMsg.setFrom(new InternetAddress(from, "思源自动化")); //设置发信人   
	            return true;   
	        } catch(Exception e) {   
	            return false;   
	        }   
	    }   
	    /**  
	     * 设置收信人 
	     * @param to String  
	     */   
	    public boolean setTo(String to){   
	        if(to == null)return false;   
	        try{   
	            mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));   
	            return true;   
	        } catch(Exception e) {   
	            return false;   
	        }     
	    }   
	      
	    /**  
	     * 设置抄送人 
	     * @param copyto String   
	     */   
	    public boolean setCopyTo(String copyto)   
	    {   
	        if(copyto == null)return false;   
	        try{   
	        mimeMsg.setRecipients(Message.RecipientType.CC,(Address[])InternetAddress.parse(copyto));   
	        return true;   
	        }   
	        catch(Exception e)   
	        { return false; }   
	    }   
	      
	    /**  
	     * 发送邮件 
	     * @throws MessagingException 
	     */   
		public boolean sendOut(boolean needCopyto)   
	    {   
	        try{   
	            mimeMsg.setContent(mp);   
	            mimeMsg.saveChanges();   
	            System.out.println("正在发送邮件....");   
	              
	            Session mailSession = Session.getInstance(props,null);   
	            Transport transport = mailSession.getTransport("smtp");   
	            transport.connect((String)props.get("mail.smtp.host"),username,password);   
	            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));  
	            if(needCopyto){
	            	transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));  
	            }
//	            transport.send(mimeMsg);   
	              
	            System.out.println("发送邮件成功！");   
	            transport.close();   
	              
	            return true;   
	        } catch(Exception e) {   
	            System.err.println("邮件发送失败！"+e);   
	            return false;   
	        }   
	    }   
	        
	    /** 
	     * 调用sendOut方法完成邮件发送
	     * @param smtp 
	     * @param from 
	     * @param to 
	     * @param copyto 
	     * @param subject 
	     * @param content 
	     * @param username 
	     * @param password 
	     * @param filename 
	     * @return 
	     * @throws MessagingException 
	     */  
	    public static boolean send(String content){  
	        Mail theMail = new Mail(Config.SMTPserver);  
	        theMail.setNeedAuth(true); //需要验证  
	        boolean needCopyto = true;
	        if(!theMail.setSubject(Config.subject)) return false;  
	        if(!theMail.setBody(content)) return false;  
	        
	        if(!Config.filename.equals("")){
	        	if(!theMail.addFileAffix(Config.filename)) return false;   
	        }
	        
	        if(!theMail.setTo(Config.to)) return false; 
	        
	        if(Config.copyTo.equals("")||Config.copyTo.equals(" ")){
	        	needCopyto = false;	        	
	        }else{
	        	if(!theMail.setCopyTo(Config.copyTo)) return false; 
	        }
	        
	        if(!theMail.setFrom(Config.from)) return false;  
	        theMail.setNamePass(Config.username,Config.password);  
	        if(!theMail.sendOut(needCopyto)) return false;  
	        return true;  
	    }  
}
