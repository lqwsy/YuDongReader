package com.yudong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 随机生成文件名
 * */
public class FileUtils {
	
	public static final String getRandomFileName(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date())+new Date().getTime()+""+SMSHttpManager.smsContentGenernator();
	}
	
}
