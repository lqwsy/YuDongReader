package com.yudong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yudong.entity.Users;
import com.yudong.service.UserService;

/**
 * 用户后台管理逻辑类
 */
@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	
	/**
	 * json数据交互测试
	 */
	@RequestMapping(value = "/jsonGet", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Users jsonGet() {
		Users user = userService.findUserById(1);
		return user;
	}
	
	/**
	 * json数据交互测试
	 */
	@RequestMapping(value = "/jsonPost", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String jsonPost(HttpServletRequest request) {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("username: "+user+"\n password: "+pass);
		return "success";
	}
	
}
