package com.wjq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2//开启swagger2支持
@ServletComponentScan//自动扫描@WebFilter注解的类 servlet和filter
@EnableCaching//开启缓存支持
public class BookShopAdminApplication {

	public static void main(String[] args) {
		//设置dev热部署为禁止重新启动,开发时设置完热部署觉得一直重启有点烦,又不想清空设置,这里设置一下开关,根据需要可以打开
		//System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(BookShopAdminApplication.class, args);
	}
//	@Bean
//	public CacheManagerCustomizer<RedisCacheManager> customizer() {
//		return new CacheManagerCustomizer<RedisCacheManager>() {
//			@Override
//			public void customize(RedisCacheManager cacheManager) {
//				Map<String,Long> map = new HashMap<>();
//				map.put("books",1000L);
//				map.put("users",2000L);
//				cacheManager.setExpires(map);
//			}
//		};
//	}
}
