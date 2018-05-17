package com.yudong.service;

import java.util.List;

import com.yudong.entity.Books;

public interface BookService {

	public void saveBook(Books book);//保存图书
	public List<Books> getBooks();//获取图书
	public void findBookById(int bookId);//根据ID查找图书
	public void findBookByName(String bookName);//根据图书名称来查找图书
	
}
