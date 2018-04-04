package com.wjq.service;

import com.wjq.dto.BookInfo;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/2/3 9:49
 * <p>@author : wjq
 */
public interface BookService {
    BookInfo save(BookInfo bookInfo);

    void remove(Long id);

    BookInfo findOne(BookInfo bookInfo);
}
