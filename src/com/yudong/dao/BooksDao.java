package com.yudong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yudong.entity.Books;

public interface BooksDao {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Books record);//保存图书
    List<Books> getBooks();//根据下载量排行，获取前七名
    List<Books> getAllBooks();
    List<Books> getBooksByAuthor(String author);
    List<Books> getBooksByUploadPerson(String uploadPerson);
    List<Books> getClassificationBooks(Integer classificationId);//根据分类名称获取图书
    List<Books> getStateBooks(int bookState);//根据图书状态获取图书
    List<Books> getDeleteBooks(String uploadPerson);//获取当前用户已删除的图书
    Books getBookById(Integer bookId);//根据图书ID获取图书
    Books getBookByName(String bookName);//根据图书名称获取图书
    List<Books> getBookBySearchName(@Param("searchBookName")String searchBookName);//根据图书名称模糊搜索图书
    int insertSelective(Books record);
    int updateByPrimaryKeySelective(Books record);//根据图书ID修改图书状态
    int updateByPrimaryKey(Books record);
    int getAllBookCount();
    List<Books> getPageBooks(@Param("startRaw")int startRaw,@Param("count")int count);//参数1,查询开始位置，参数2，查询条数
}