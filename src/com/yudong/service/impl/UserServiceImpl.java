package com.yudong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yudong.dao.UsersDao;
import com.yudong.entity.Users;
import com.yudong.service.UserService;
/**
 * 用户服务接口实现类
 * */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersDao usersDao;
	
	@Override
	public Users findUserById(int userId) {
		return usersDao.selectByPrimaryKey(userId);
	}

	@Override
	public Users findUserByUserName(String userName) {
		return usersDao.selectByUserName(userName);
	}

	@Override
	public int addUser(Users user) {
		return usersDao.insert(user);
	}

}
