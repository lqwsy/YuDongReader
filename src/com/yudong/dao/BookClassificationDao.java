package com.yudong.dao;

import com.yudong.entity.BookClassification;

public interface BookClassificationDao {
    int deleteByPrimaryKey(Integer bookClassificationId);

    int insert(BookClassification record);

    int insertSelective(BookClassification record);

    BookClassification selectByPrimaryKey(Integer bookClassificationId);

    int updateByPrimaryKeySelective(BookClassification record);

    int updateByPrimaryKey(BookClassification record);
}