package com.wjq.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wjq.dto.BookCondition;
import com.wjq.dto.BookInfo;
import com.wjq.repository.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>Description : bookshop-admin
 * <p>Date : 2018/1/18 14:09
 * <p>@author : wjq
 */
@Api(value = "图书BookController" , tags = "图书控制器")
@RestController
@RequestMapping("/book")
public class BookController{
    @Autowired
    private BookRepository bookRepository;
    @GetMapping
    @JsonView(BookInfo.BookListView.class)
    @ApiOperation(value = "查询图书信息",notes = "这是查询图书信息接口")
    public List<BookInfo> query2(BookCondition bookCondition, @PageableDefault(size = 10) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if(authentication != null) {
            System.out.println(authentication.getPrincipal());
        }
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getOffset());
        System.out.println(pageable.getPageSize());
        System.out.println(bookCondition.toString());
        return new ArrayList<>();
    }
    //并发map
    private ConcurrentMap<Long,DeferredResult<BookInfo>> map = new ConcurrentHashMap<>();

    @GetMapping("/getBook/{id:\\d}")//正则表达式,表示id只能为个位数
    @JsonView(BookInfo.BookDetailView.class)
    @ApiOperation(value = "查询图书详情详细",notes = "图书详情")
    public BookInfo query3(@ApiParam("图书ID") @PathVariable(value = "id") Long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication);
//        if(authentication != null) {
//            System.out.println(authentication.getPrincipal());
//        }
//        BookInfo bookInfo = new BookInfo();
//        bookInfo.setName("战争与和平");
        System.out.println("从数据库中查询");
        return bookRepository.findOne(id);
//        long start = new Date().getTime();
//        System.out.println(Thread.currentThread().getName() + "主线程开始");
//        Callable<BookInfo> result = () -> {
//            Thread.sleep(1000);
//            System.out.println(Thread.currentThread().getName() + "线程开始");
//            BookInfo bookInfo = new BookInfo();
//            bookInfo.setName("泰坦尼克号");
//            bookInfo.setPublishDate(new Date());
//            System.out.println(Thread.currentThread().getName() + "线程返回:" + (new Date().getTime() - start));
//            return bookInfo;
//        };
//        DeferredResult<BookInfo> result = new DeferredResult<>();
//        map.put(id,result);
//        System.out.println(Thread.currentThread().getName() + "主线程开始，耗时:" + (new Date().getTime() - start));
//        return result;
//        throw new RuntimeException("test");
//        System.out.println(id);
    }
    //监听，观察者模式
    private void listenMessage(BookInfo bookInfo) {
        map.get(bookInfo.getId()).setResult(bookInfo);
    }

    //    @GetMapping("/{id:\\d}")//正则表达式,表示id只能为个位数
//    @JsonView(BookInfo.BookDetailView.class)
//    public BookInfo query3(@PathVariable(value = "id") Long id,@CookieValue String token,@RequestHeader String author) {
//        System.out.println(token);
//        System.out.println(author);
//        System.out.println(id);
//        BookInfo bookInfo = new BookInfo();
//        bookInfo.setName("战争与和平");
//        return bookInfo;
//    }
//    @RequestMapping(value = "/book",method = RequestMethod.GET)
//    @GetMapping
//    public List<BookInfo> query(@RequestParam(name = "bookname", defaultValue = "hello") String name, long categoryId) {
//        System.out.println(name);
//        return new ArrayList<BookInfo>();
//    }
    @PostMapping
    public BookInfo create(@Valid @RequestBody BookInfo bookInfo, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(bookInfo.toString());
        bookInfo.setId(1L);
        return bookInfo;
    }

    @PutMapping
    public BookInfo update(@Valid @RequestBody BookInfo bookInfo, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(bookInfo.toString());
        bookInfo.setId(1L);
        return bookInfo;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findOne(id);
        bookRepository.delete(id);
        System.out.println("..................................................");
    }

}
