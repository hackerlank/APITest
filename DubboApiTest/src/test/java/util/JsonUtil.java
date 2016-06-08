package util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.google.gson.JsonParseException;

public class JsonUtil {
	
	private final static Log logger = LogFactory.getLog(JsonUtil.class);
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	     /**
	     * 用gson 来json字符串的格式化
	     * @author  
	     * @return
	     */ 
	
	public static String jsonFormatter(String uglyJSONString){
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJSONString);
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;	
	}
	
	/**
	 * 将obj转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object obj){
		
		return gson.toJson(obj);
	}
	
	/**
	 * 判断字符串是否是错误的json对象
	 * @param json
	 * @return
	 */
	public static boolean isBadJson(String json) {
		return !isGoodJson(json);
	}
	
	/**
	 * 判断字符串是否是有效的json对象
	 * @param json json字符串
	 * @return
	 */
	public static boolean isGoodJson(String json) {
		if (StringUtils.isBlank(json)) {
			return false;
		}
		try {
			new JsonParser().parse(json);
			return true;
		} catch (JsonParseException e) {
			logger.error("bad json: " + json);
			return false;
		}
	}
}
