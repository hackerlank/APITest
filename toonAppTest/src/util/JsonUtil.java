package util;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.*;

public class JsonUtil {
	
	     /**
	     * 用gson 来json字符串的格式化
	     * @author  
	     * @return
	     */ 
	
	public static String jsonFormatter(String uglyJSONString){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJSONString);
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;	
	}
	
	public static String JackSonFormat(Object object){
		String jsonStr = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;	
	}
	
	/**
	 * 用jackson来格式化json
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	
	public static String JackSonFormat(String jsonString) throws Exception{
		String jsonStr = "";
		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(jsonString, Object.class);
		jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);	
		return jsonStr;	
	}
}
