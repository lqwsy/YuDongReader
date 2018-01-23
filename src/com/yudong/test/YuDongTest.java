package com.yudong.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yudong.entity.Users;
import com.yudong.service.UserService;
import com.yudong.utils.JavaMD5Util;
/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:mybatis-bean.xml" })

public class YuDongTest {

	@Autowired
	private UserService userService;
	
	//测试框架搭建是否成功
	@Test
	public void test() {
		
		Users user = userService.findUserById(1);
		if(user!=null){
			System.out.println("userName : "+user.getUserName());
			System.out.println("userNickName : "+user.getUserNickName());
		}else{
			System.out.println("empty result!");
		}
	}
	
	
	//测试插入数据
	@Test
	public void testInsert(){
		String salt = JavaMD5Util.generatorSalt();//随机生成5位长度的盐值
		String password = JavaMD5Util.encode("root", salt);
		System.out.println(salt);
		System.out.println(password);
		Users user = new Users();
		user.setUserName("13413613754");
		user.setUserNickName("小白");
		user.setHeadImage("default.jpg");
		user.setSalt(salt);
		user.setPassword(JavaMD5Util.encode("123456", salt));
		user.setRole(2);
		user.setRegisteTime(new Date());
		user.setUserState(1);
		
		System.out.println("result is : "+userService.addUser(user));
		
	}

}
