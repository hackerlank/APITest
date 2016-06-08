package util;

//	import php.java.script.InvocablePhpScriptEngine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import php.java.script.InvocablePhpScriptEngine;
	/**
	 * 
	 * @author 
	 * 
	 */
public class UsePhpFunction {
	
	 ScriptEngineManager manager = new ScriptEngineManager();

	 // Return Javascript engine by name
//	 ScriptEngine jsengine = manager.getEngineByName("php");
	InvocablePhpScriptEngine jsengine = new InvocablePhpScriptEngine();
	
	   UsePhpFunctions phpFunction= null;
	   	   
	   
//
	 public UsePhpFunction() {
		try {
			FileResourceUtil phpFileRes = new FileResourceUtil("php_file/daojia.php");
			String phpFile = phpFileRes.getConfigFile();
//			System.out.println(phpFile);
			FileReader fr = new FileReader( phpFile);  
						  
		    jsengine.eval(fr); //上面的php文件	  		
	
		  } catch (FileNotFoundException e) {	
			  e.printStackTrace();
		  } catch (ScriptException e) {
			  e.printStackTrace();
		  }catch(NullPointerException e){
			  System.out.println("发生异常的原因为   :"+e.getMessage());
		  }
	  if( jsengine instanceof Invocable) {
			  Invocable inv = (Invocable)  jsengine;  //上面的接口
			  
			  phpFunction = (UsePhpFunctions) inv.getInterface(UsePhpFunctions.class);
		  } else {
			  System.out.println("php脚本不正确");
			  System.out.println( jsengine.toString());
		  }
			  
	 }
	 
	 public String getToken(String MemberID, String host){
		 return phpFunction.GetToken(MemberID, host);
	 }
	 
	 public String GetCheckDigit(String msg){
		 return phpFunction.GetCheckDigit(msg);
	 }
	 
	 public String GetTest(String msg){
		 return phpFunction.GetTest(msg);
	 }
	
	 public String GetPeiSongCheck(String msg){
		 return phpFunction.GetPeiSongCheck(msg);
	 }
		

	 public interface UsePhpFunctions {			// interface  下的方法名需要和php函数名一致
			

			public String GetToken(String MemberID, String host);
			
			public String GetCheckDigit(String msg);
			
			public String GetTest(String msg);
			
			public String GetPeiSongCheck(String msg);
			
				
	}
	 
	public static void main(String[] args) {
		
			
		String text = "{\"params\": {\"update_content\":\"1\",\"city_id\": \"2419\"},\"sign\": \"6c0910e7f3406c1f722c643a06bc5c50\",\"time\": \"123456\"}";
		UsePhpFunction phpFunction = new UsePhpFunction();
		
		String str = "13581535754";
		String host = "app.daojia.com.cn";
		String token = phpFunction.getToken(str, host);
//		System.out.println(token);
		
		
		String msg = "{\"Command\": \"GetCityList\",\"CheckDigit\": \"0\",\"SequenceID\": \"0\",\"Body\":{\"ServiceCode\":\"00005\"}}";
		System.out.println("0000----" + phpFunction.GetTest(msg)); 
		
		String msg2 = "{\"Command\":\"CreateOrder\",\"Body\":{\"Address\":\"北京市朝阳区北辰世纪中心A座16层\",\"ServiceCode\":\"00005\",\"CityID\":1,\"AreaID\":67,\"RestaurantID\":12597,\"Remark\":\"测试单\",\"DeliveryTime\":\"2015-05-12 16:25\",\"Linkman\":\"徐建勇\",\"Gender\":1,\"Mobile\":\"18600061932\",\"Invoice\":\"\",\"Card\":null,\"Subtotal\":1230.0,\"DeliveryCost\":6.0,\"PackagingCost\":17.0,\"CollectCharges\":0.0,\"PrivateID\":0.0,\"OrderFoodItems\":[{\"Remark\":null,\"FoodID\":2162431,\"Quantity\":15.0},{\"Remark\":null,\"FoodID\":2162433,\"Quantity\":13.0}]},\"CheckDigit\":4207935826,\"SequenceID\":\"309981\"}";
	//	System.out.println(phpFunction.GetTest(msg2));
		
		// [{\"Command\":\"GetEvents\",\"Body\":{\"LastVisitTime\":\"2015-06-02 09:44:34\",\"Latitude\":40.025341,\"Longitude\":116.479746,\"EventType\":\"0\",\"OrderSet\":\"[]\"},\"SequenceID\":12,\"CheckDigit\":3478718889}]
		String msgPeisong = "{\"LastVisitTime\":\"2015-06-02 09:44:34\",\"Latitude\":40.025341,\"Longitude\":116.479746,\"EventType\":\"0\",\"OrderSet\":\"[]\"}";  //3478718889
		System.out.println(phpFunction.GetPeiSongCheck(msgPeisong));
	}

}