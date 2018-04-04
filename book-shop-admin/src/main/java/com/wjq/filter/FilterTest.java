package com.wjq.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/2/2 9:23
 * <p>@author : wjq
 */
@WebFilter
public class FilterTest implements Filter{
    private static Logger logger = LoggerFactory.getLogger(FilterTest.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器被初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        logger.info("过滤到的请求{}",uri);
        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {
        logger.info("过滤器被销毁");
    }
}
