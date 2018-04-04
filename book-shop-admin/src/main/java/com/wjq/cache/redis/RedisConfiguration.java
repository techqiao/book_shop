package com.wjq.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/2/6 11:20
 * <p>@author : wjq
 */
@Configuration
@PropertySource(value = "classpath:redis.properties")
public class RedisConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
    @Bean
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.pool.max-active}") int maxTotal,
                                                  @Value("${spring.redis.pool.max-idle}") int maxIdle,
                                                  @Value("${spring.redis.pool.max-wait}") int maxWaitMillis,
                                                  @Value("${spring.redis.pool.min-idle}") int minIdle) {
        logger.info("JedisPoolConfig连接池注入成功.................");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinIdle(minIdle);
        return config;
    }
    @Bean
    public JedisPool jedisPool(JedisPoolConfig config,
                               @Value("${spring.redis.host}") String host,
                               @Value("${spring.redis.port}") int port,
                               //@Value("${spring.redis.password}") String password,
                               @Value("${spring.redis.timeout}") int timeout,
                               @Value("${spring.redis.pool.max-active}") int maxTotal,
                               @Value("${spring.redis.pool.max-idle}") int maxIdle,
                               @Value("${spring.redis.pool.max-wait}") int maxWaitMillis,
                               @Value("${spring.redis.pool.min-idle}") int minIdle) {
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinIdle(minIdle);
        logger.info("JedisPool注入成功.....................");
//        return new JedisPool(config, host, port, timeout, password);
        return new JedisPool(config, host, port, timeout);
    }

}
