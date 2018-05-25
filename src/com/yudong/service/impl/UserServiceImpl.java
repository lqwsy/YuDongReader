package com.yudong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yudong.dao.UsersDao;
import com.yudong.entity.Users;
import com.yudong.service.UserService;
import com.yudong.utils.JavaMD5Util;
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
	public boolean addUser(Users user) {
		if(usersDao.insert(user)!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(Users user){
		if(usersDao.updatePasswordByUserName(user)!=0){
			return true;
		}
		return false;
	}

	@Override
	public Users findUserByUserNameAndPsw(Users user) {
		Users curUser = usersDao.selectByUserName(user.getUserName());
		if(curUser!=null){
			
			System.out.println("localpassword "+curUser.getPassword());
			System.out.println("inputpassword "+JavaMD5Util.encode(user.getPassword(), curUser.getSalt()));
			
			if(curUser.getPassword().equals(JavaMD5Util.encode(user.getPassword(), curUser.getSalt()))){
				return curUser;
			}
		}
		return null;
	}

	@Override
	public boolean updateUserInfo(Users user) {
		if(usersDao.updateByPrimaryKeySelective(user)!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<Users> getAllUsers() {
		return usersDao.selectAllUsers();
	}

	@Override
	public List<Users> getUserByRold(int role) {
		return usersDao.getUserByRold(role);
	}

}
