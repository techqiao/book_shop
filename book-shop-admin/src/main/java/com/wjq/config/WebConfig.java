package com.wjq.config;

import com.wjq.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/20 11:00
 * <p>@author : wjq
 */
@Configuration//声明一个配置文件
public class WebConfig extends WebMvcConfigurerAdapter {
    //注入一个过滤器到mvc容器
    @Bean
    public FilterRegistrationBean characterEncodingFilterRegister(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF8");
        filter.setForceEncoding(true);
        registrationBean.setFilter(filter);
        List<String> list = new ArrayList<>();
        list.add("/*");
        registrationBean.setUrlPatterns(list);
        return  registrationBean;
    }
    @Autowired
    private TimeInterceptor timeInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将拦截器添加到mvc容器
        registry.addInterceptor(timeInterceptor);
    }
//    @Bean
//    public EhCacheCacheManager cacheManager(CacheManager enCacheManager) {
//        return new EhCacheCacheManager(enCacheManager);
//    }
}
