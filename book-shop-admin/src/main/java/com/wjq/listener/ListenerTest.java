package com.wjq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/2/2 9:20
 * <p>@author : wjq
 */
@WebListener
public class ListenerTest implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(ListenerTest.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("监听器被初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("监听器被销毁");
    }
}
