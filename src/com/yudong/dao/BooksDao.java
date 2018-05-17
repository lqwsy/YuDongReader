package com.yudong.dao;

import java.util.List;

import com.yudong.entity.Books;

public interface BooksDao {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Books record);
    
    List<Books> getBooks();

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);
}