package com.wjq.repository;

import com.wjq.dto.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/31 11:47
 * <p>@author : wjq
 */
public interface BookRepository extends JpaRepository<BookInfo,Long>{
    @Query("select b from BookInfo b where 1=1")
    List<BookInfo> findBookAll();
}
