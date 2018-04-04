package com.wjq.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/31 11:28
 * <p>@author : wjq
 */
@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        ServletRegistrationBean pojo = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数
        pojo.addInitParameter("allow","127.0.0.1");
        pojo.addInitParameter("deny","192.168.1.188");
        pojo.addInitParameter("loginUsername","kobe");
        pojo.addInitParameter("loginPassword","123456");
        //是否能够重置数据
        pojo.addInitParameter("resetEnable","false");

        return pojo;
    }

    @Bean
    public FilterRegistrationBean  druidStatFilter(){
        FilterRegistrationBean pojo = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        pojo.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        pojo.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return pojo;
    }
}
