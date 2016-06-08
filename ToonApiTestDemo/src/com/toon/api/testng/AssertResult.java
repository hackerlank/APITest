package com.toon.api.testng;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import com.toon.api.utils.Log;

public class AssertResult extends Assertion{

	/** 重写asserion方法 设置失败检查监听
	 * **/
	@Override
	public void onAssertFailure(IAssert assertCommand){
		String message=assertCommand.getMessage()!=null?assertCommand.getMessage():"失败";
		Log.logInfo("执行失败，失败消息：["+message+"]");
	}
}
