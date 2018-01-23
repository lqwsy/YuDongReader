package com.yudong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yudong.entity.Users;
import com.yudong.service.UserService;
import com.yudong.utils.JavaMD5Util;

/**
 * 用户后台管理逻辑类
 */
@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	
	/**
	 * json数据交互
	 * GET测试
	 */
	@RequestMapping(value = "/jsonGet", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Users jsonGet() {
		Users user = userService.findUserById(1);
		return user;
	}
	
	/**
	 * json数据交互
	 * POST测试
	 */
	@RequestMapping(value = "/jsonPost", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String jsonPost(HttpServletRequest request) {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("username: "+user+"\n password: "+pass);
		return "success";
	}
	
	
	/**
	 * 客户端登录控制
	 */
	@RequestMapping(value = "/clientLoginController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int clientLoginController(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = userService.findUserByUserName(userName);
		if(user == null){
			return 3;//账号不存在
		}else {
			if (user.getPassword().equals(JavaMD5Util.encode(password, user.getSalt()))){
				return 1;//匹配成功
			}else {
				return 4;//密码错误
			}
		}
	}
}
