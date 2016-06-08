package com.toon.api.testng.Liseners;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

public class TestNGListener extends TestListenerAdapter{

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		//super.onFinish(testContext);
		System.out.println(testContext.getName());
	}

}
