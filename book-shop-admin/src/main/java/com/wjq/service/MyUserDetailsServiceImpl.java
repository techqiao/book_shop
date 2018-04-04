package com.wjq.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/31 10:11
 * <p>@author : wjq
 */
@Component
//import org.springframework.security.core.userdetails.UserDetailsService;
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.equals(username, "kobe")) {
            return new User("kobe","123456",new ArrayList<>());
        }
        return null;
    }
}
