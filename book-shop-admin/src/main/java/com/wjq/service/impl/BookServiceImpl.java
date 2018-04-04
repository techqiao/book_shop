package com.wjq.service.impl;

import com.wjq.cache.redis.RedisService;
import com.wjq.dto.BookInfo;
import com.wjq.repository.BookRepository;
import com.wjq.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/2/3 9:51
 * <p>@author : wjq
 */
@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    BookRepository bookRepository;
//    @Autowired
//    RedisService redisService;

    @Override
    //在方法执行前，Spring先查看缓存中是否有数据，有直接返回，没有调用方法将返回值放入缓存
    //cacheNames是value属性的别名，代表缓存的策略，可以设置它的时间，数量，相当于mysql数据库的某个表
    //key 就是设置的key,通过key去拿缓存数据
    //方法的返回值就是缓存的数据
    //sync 并发访问，true会执行语句，从数据源去查
    //keyGenerator 默认key的生成策略，方法名+参数,也可以覆盖重写自己的生成策略
    //condition spel表达式，#bookInfo.id
    @Cacheable(cacheNames = "TESTCAFFEINE",key = "#bookInfo.id",sync = true)
    public BookInfo findOne(BookInfo bookInfo) {
//        boolean flag = redisService.existKey("name", null);
//        logger.info("从缓存中检查key为name的值是否存在:{}",flag);
        BookInfo b = bookRepository.findOne(bookInfo.getId());
        logger.info("为key为{}做了缓存",bookInfo.getId());
        bookRepository.count();
        return b;
    }

    @Override
    //缓存新增的或者更新缓存数据，缓存名称为bookInfo,数据的key为id
    @CachePut(value = "TESTCAFFEINE",key = "#bookInfo.id")
    public BookInfo save(BookInfo bookInfo) {
        BookInfo b = bookRepository.save(bookInfo);
        logger.info("为key为{}做了缓存",bookInfo.getId());
        return b;
    }

    @Override
    //从缓存中删了key为id的数据
    @CacheEvict(value = "TESTCAFFEINE")
    public void remove(Long id) {
        logger.info("删除了key为{}的数据");
        //bookRepository.delete(id);
    }

}
