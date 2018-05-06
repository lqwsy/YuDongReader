package com.yudong.service;

import com.yudong.entity.Users;

public interface UserService {
	
	public Users findUserById(int userId);//根据ID查找用户
	public Users findUserByUserName(String userName);//根据用户名查找用户
	public int addUser(Users user);//添加用户
	public int updatePassword(Users user);//更新密码
	public Users findUserByUserNameAndPsw(Users user);//根据用户名密码查找用户
	
}
