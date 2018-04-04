package com.wjq.controller;

import com.wjq.dto.BookInfo;
import com.wjq.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.*;


/**
 * <p>Description : book_shop
 * <p>Date : 2018/2/3 9:09
 * <p>@author : wjq
 */
@RestController
@RequestMapping("book2")
public class BookController2 {
    //ConcurrentMapCacheManager
    @Autowired
    private BookService bookService;

    @GetMapping("{bookInfo}")
    public BookInfo findOne(@PathVariable(value = "bookInfo") BookInfo bookInfo) {
        return bookService.findOne(bookInfo);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        bookService.remove(id);
    }

    @PostMapping
    public BookInfo save(BookInfo bookInfo) {
        return bookService.save(bookInfo);
    }
}
