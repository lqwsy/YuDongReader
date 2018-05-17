package com.yudong.dao;

import com.yudong.entity.Users;

public interface UsersDao {
	
	Users selectByPrimaryKey(Integer userId);
	Users selectByUserName(String userName);
	int insert(Users record);
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