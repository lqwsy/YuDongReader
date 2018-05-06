package com.yudong.common;
/**
 * 存放常量的类
 * */
public class Constants {
	
	public final static String USER_KEY = "CURRENT_USER";
	public final static String LOGIN_RESULT = "LOGIN_RESULT";
	public final static String REGISTER_RESULT = "REGISTER_RESULT";
	public final static String VER_CODE = "VER_CODE";
	
    /**
     * 发送验证码url
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

    /**
     * 开发者注册后系统自动生成的账号，可在官网登录后查看
     */
    public static final String ACCOUNT_SID = "6db6fd5109e440389964837643f45930";

    /**
     * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
     */
    public static final String AUTH_TOKEN = "276399b467d44e789cc7b281ce2b3c09";
    /**
     * 响应数据类型, JSON或XML
     */
    public static final String RESP_DATA_TYPE = "json";
	
}
