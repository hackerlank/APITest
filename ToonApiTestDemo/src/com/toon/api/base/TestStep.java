package com.toon.api.base;

import java.util.ArrayList;
import java.util.List;

import com.toon.api.types.TestStepType;

public abstract class TestStep {
	
	//步骤编号，设置步骤的次序
	public String stepID;
	
	TestStep(TestCase testCase){
		this.fatherCase=testCase;
	}
	
	//步骤所属case
	public TestCase fatherCase;
	
	//测试步骤类型
	public int testStepType;
	 
	//是否具有检查点
	public boolean havaCheckPoint=false;
	 
	//执行测试步骤
	public void run(){
		if(checkPointList==null||checkPointList.size()==0){
			return ;
		}
	}
	
	//检查点列表
	public List<CheckPoint> checkPointList=null;
	
	//添加检查点
	public void addCheckPoint(CheckPoint checkPoint){
		if(this.testStepType!=TestStepType.DATATRANSFOR){
			this.havaCheckPoint=true;
			if(this.checkPointList==null){
				this.checkPointList=new ArrayList<CheckPoint>();
			}
			this.checkPointList.add(checkPoint);
		}
	}
}
