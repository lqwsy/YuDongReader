package com.yudong.dao;

import java.util.List;

import com.yudong.entity.Users;

public interface UsersDao {
	
	Users selectByPrimaryKey(Integer userId);
	Users selectByUserName(String userName);
	List<Users> selectAllUsers();
	int insert(Users record);
	List<Users> getUserByRold(int role);
	int updatePasswordByUserName(Users user);
	int updateByPrimaryKeySelective(Users record);
	
	
	
//    int deleteByPrimaryKey(Integer userId);
//
//
//    int insertSelective(Users record);
//
//
//
//    int updateByPrimaryKey(Users record);


}