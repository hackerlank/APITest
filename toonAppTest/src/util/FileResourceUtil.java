package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileResourceUtil {

	/**
	 * @return 返回配置文件内容
	 * @author haoyuexun
	 */
	private  String configFile = "";
	
	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		
		String config = System.getProperty("user.dir") + "/" + configFile;
//		System.out.println(configFile);
		try {
			config = URLDecoder.decode(configFile,"utf-8");
			this.configFile = config;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public FileResourceUtil() {
		
	}
	
    /**
     * 构造方法
     * @param configName  properties文件,该文件必须在项目根目录下
     */
	public FileResourceUtil(String configName) {
		this.setConfigFile(configName);
	}
	
   
/*
	public FileResourceUtil(String configName) {
		int posBin = 0;
		
//		configFile = (getClass().getResource("/").getPath()).substring(1);
//		posBin = configFile.indexOf("bin");
//		configFile = configFile.substring(0, posBin) + configName;
		
		configFile = System.getProperty("user.dir") + "/" + configName;
//		System.out.println(configFile);
		
		try {
			configFile = URLDecoder.decode(configFile,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println(configFile); 
	}*/
	
	/**
	 * 得到配置文件的 value
	 * @param configKey  配置文件的 key
	 * @return
	 */
	public String getConfigValue(String configKey){
		Properties Config = new Properties();
		InputStream inputStream = null;
		BufferedReader bf = null;
		try {
			inputStream = new FileInputStream(configFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
			 bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			 e1.printStackTrace();
		}
	    try {
			Config.load(bf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    String pValue = Config.getProperty(configKey);
    
	    return pValue;
		
	}
	
	//更新某一属性
	public void updatePropertiesFile(String key, String value) throws IOException
	{
		Properties property=new Properties();
		InputStream input=null;
		BufferedReader reader=null;
		OutputStream out=null;
		Map<String, String> tempProperty=new HashMap<String,String>();
		Set<Object> keySet=null;
		try
		{
			input=new FileInputStream(configFile);
			reader=new BufferedReader(new InputStreamReader(input,"utf-8"));
			property.load(reader);
			keySet=property.keySet();
			for(Iterator<Object> it=keySet.iterator(); it.hasNext(); )
			{
				String keyName=(String)it.next();
				if(keyName.equalsIgnoreCase(key))
				{
					tempProperty.put(key, value);
				}
				else
				{
					tempProperty.put(keyName, property.getProperty(keyName));
				}
			}
			
			property.putAll(tempProperty);
			
			out=new FileOutputStream(configFile);
			property.store(out, "Update token for key "+key);
			input.close();
			out.close();
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			input.close();
			out.close();
		}
		
	}

    //写资源文件，含中文  
    public  void writePropertiesFile(String key, String value)  
    {  
    	Properties Config = new Properties();
    	InputStream inputStream = null;
		BufferedReader bf = null;
    	OutputStream ops = null;
	
        try  
        {   inputStream = new FileInputStream(configFile);
            ops = new FileOutputStream(configFile);  
            bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            Config.load(bf);
            bf.close();
            Config.setProperty(key, value);   
            Config.store(ops, "配置文件更改成功");  
            ops.close();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
	
	
	
	public static void main(String[] args) {
		
//		FileResourceUtil fru = new FileResourceUtil("token.properties");
//		fru.writePropertiesFile("redis.pool.maxWait", "4000");
	}
	
}
