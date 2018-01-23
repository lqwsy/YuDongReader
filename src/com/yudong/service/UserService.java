package com.yudong.service;

import com.yudong.entity.Users;

public interface UserService {
	
	public Users findUserById(int userId);//根据ID查找用户
	public Users findUserByUserName(String userName);//根据用户名查找用户
	public int addUser(Users user);//添加用户
	
}
