package com.toon.api.testng;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import com.toon.api.utils.Log;

public class AssertResult extends Assertion{

	/** ��дasserion���� ����ʧ�ܼ�����
	 * **/
	@Override
	public void onAssertFailure(IAssert assertCommand){
		String message=assertCommand.getMessage()!=null?assertCommand.getMessage():"ʧ��";
		Log.logInfo("ִ��ʧ�ܣ�ʧ����Ϣ��["+message+"]");
	}
}
