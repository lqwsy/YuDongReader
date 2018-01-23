package com.yudong.dao;

import com.yudong.entity.Users;

public interface UsersDao {
	
	Users selectByPrimaryKey(Integer userId);
	Users selectByUserName(String vUserName);
	int insert(Users record);
	
	
	
//    int deleteByPrimaryKey(Integer userId);
//
//
//    int insertSelective(Users record);
//
//
//    int updateByPrimaryKeySelective(Users record);
//
//    int updateByPrimaryKey(Users record);


}