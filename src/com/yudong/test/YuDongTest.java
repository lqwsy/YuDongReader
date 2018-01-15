package com.yudong.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yudong.entity.Users;
import com.yudong.service.UserService;
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
		}
	}
	
	//测试对象转换成json
	@SuppressWarnings("static-access")
	@Test
	public void testObjectToString(){
		Users user = userService.findUserById(1);
		
	}

}
