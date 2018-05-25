package com.yudong.service.impl;

import java.util.List;

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
	public boolean saveBook(Books book) {
		if(bookDao.insert(book)!=0){
			return true;
		}
		return false;
	}

	@Override
	public Books findBookById(int bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public Books findBookByName(String bookName) {
		return bookDao.getBookByName(bookName);
	}

	@Override
	public List<Books> getBooks() {
		return bookDao.getBooks();
	}

	@Override
	public List<Books> getClassificationBooks(Integer classificationId) {
		return bookDao.getClassificationBooks(classificationId);
	}

	@Override
	public List<Books> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public List<Books> getBooksByAuthor(String author) {
		return bookDao.getBooksByAuthor(author);
	}

	@Override
	public boolean updateBookState(Books book) {
		if(bookDao.updateByPrimaryKeySelective(book)!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<Books> searchBooks(String searchBookName) {
		return bookDao.getBookBySearchName(searchBookName);
	}

	@Override
	public List<Books> getMyBooks(String uploadPerson) {
		return bookDao.getBooksByUploadPerson(uploadPerson);
	}

	@Override
	public List<Books> getMyDeleteBooks(String uploadPerson) {
		return bookDao.getDeleteBooks(uploadPerson);
	}

	@Override
	public List<Books> getStateBooks(Integer bookState) {
		return bookDao.getStateBooks(bookState);
	}

	@Override
	public List<Books> getPageBooks(int startRaw, int count) {
		return bookDao.getPageBooks(startRaw, count);
	}

	@Override
	public int getAllBookCount() {
		return bookDao.getAllBookCount();
	}

	

}
