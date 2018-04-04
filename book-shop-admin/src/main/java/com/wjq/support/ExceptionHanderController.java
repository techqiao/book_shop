package com.wjq.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/20 10:29
 * <p>@author : wjq
 */
@RestControllerAdvice//返回json格式给前端
public class ExceptionHanderController {
    //处理RuntimeException异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)//http状态码
    public Map<String,Object> handerException(RuntimeException ex){
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("result","error");
        map.put("msg",ex.getMessage());
        return  map;
    }
}
