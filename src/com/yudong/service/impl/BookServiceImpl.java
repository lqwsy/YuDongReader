package com.yudong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yudong.dao.BooksDao;
import com.yudong.entity.Books;
import com.yudong.service.BookService;

/**
 * 图书服务接口实现类
 * */
@Service("bookService")
public class BookServiceImpl implements BookService{
	
	@Autowired
	BooksDao bookDao;

	@Override
	public void saveBook(Books book) {
		bookDao.insert(book);
	}

	@Override
	public void findBookById(int bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findBookByName(String bookName) {
		// TODO Auto-generated method stub
		
	}

	

}
