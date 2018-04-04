package com.wjq.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description : girl
 * <p>Date : 2017/12/28 10:45
 * <p>@author : wjq
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /*    @Before("execution(public * com.wjq.controller.GirlController.AllList(..))")
        public void log(){
            System.out.println("before......");
        }*/
    @Pointcut("execution(public * com.wjq.controller.*.*(..))")
    public void log() {
        //System.out.println("before......");
        //logger.info("111111111111");
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        //System.out.println("before............");
        //logger.info("222222222222");
        //获取RequestContextHolder对象,从attributes里面获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("method={}", joinPoint.getArgs());
        //返回的内容@AfterReturning

    }

    @After(value = "log()")
    public void doAfter() {
        //System.out.println("after......");
        logger.info("11111111111");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void afterReturning(Object object) {
        logger.info("response={}", object.toString());
    }
}
