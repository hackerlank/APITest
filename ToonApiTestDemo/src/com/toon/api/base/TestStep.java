package com.toon.api.base;

import java.util.ArrayList;
import java.util.List;

import com.toon.api.types.TestStepType;

public abstract class TestStep {
	
	//�����ţ����ò���Ĵ���
	public String stepID;
	
	TestStep(TestCase testCase){
		this.fatherCase=testCase;
	}
	
	//��������case
	public TestCase fatherCase;
	
	//���Բ�������
	public int testStepType;
	 
	//�Ƿ���м���
	public boolean havaCheckPoint=false;
	 
	//ִ�в��Բ���
	public void run(){
		if(checkPointList==null||checkPointList.size()==0){
			return ;
		}
	}
	
	//�����б�
	public List<CheckPoint> checkPointList=null;
	
	//��Ӽ���
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
