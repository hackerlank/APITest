package com.toon.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.toon.api.data.RunningData;

public class Common {

	public static void createDevicesId(){
		String deviceId=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		RunningData.setProperty("deviceId",deviceId, true);
	}
}
