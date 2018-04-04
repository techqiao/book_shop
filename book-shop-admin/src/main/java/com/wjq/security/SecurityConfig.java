package com.wjq.security;

import com.wjq.config.BookShopAuthenticationSuccessHandler;
import com.wjq.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/29 14:45
 * <p>@author : wjq
 */
@EnableWebSecurity//开启Security验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;
    @Autowired
    private BookShopAuthenticationSuccessHandler handler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//表单
               // .loginPage("/login2.html")
                .loginProcessingUrl("/auth")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(handler)//处理登录成功之后的
                .and().httpBasic().and()//basic
                .authorizeRequests()//request请求
                .antMatchers("/admin","/login2.html","/auth").permitAll()//Get请求不需要身份认证 也可以antMatchers("/book/*")
                .anyRequest().authenticated();//除了上面的请求剩下的都需要认证
        http.csrf().disable();
//        http.httpBasic()
//                .and()
//                .authorizeRequests()//request请求
//                .antMatchers("/book").permitAll()//Get请求不需要身份认证 也可以antMatchers("/book/*")
//                .anyRequest().authenticated();//除了上面的请求剩下的都需要认证
    }
}
