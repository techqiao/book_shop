package com.wjq.jdbc;

import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * <p>Description : jdbc工具类
 * <p>Date : 2018/1/5 13:39
 * <p>@author : wjq
 */
public class JDBCUtil {
    //获取连接connection
    public static Connection getConnection() throws Exception {
//        String url = "jdbc:mysql:///bookshop";
//        String user = "root";
//        String password ="root";
//        String driverClass ="com.mysql.jdbc.Driver";

        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String url = properties.getProperty("spring.datasource.url");
        String user = properties.getProperty("spring.datasource.username");
        String password =properties.getProperty("spring.datasource.password");
        String driverClass =properties.getProperty("spring.datasource.driver-class-name");

        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url,user,password);
        return  connection;
    }
    //释放资源

    public static void release(ResultSet resultSet, Statement statement,Connection connection){
        if(!StringUtils.isEmpty(resultSet)){

        }
    }

}
