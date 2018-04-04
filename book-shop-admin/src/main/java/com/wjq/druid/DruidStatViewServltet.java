package com.wjq.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/31 11:17
 * <p>@author : wjq
 */
@WebServlet(urlPatterns = "/admin",
            initParams = {
                @WebInitParam(name="allow",value = "127.0.0.1"),//ip白名单
                    @WebInitParam(name = "deny",value = "192.168.1.188"),//ip黑名单
                    @WebInitParam(name ="loginUsername",value = "kobe"),//用户名
                    @WebInitParam(name ="loginPassword",value = "123456"),//密码
            })
public class DruidStatViewServltet extends StatViewServlet {

}
