package com.ssyvsse.configuration;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * // 开启支持缓存
 * 
 * @author llb
 *
 * @Date 2017年12月28日 下午7:45:48
 */
@Configuration
@EnableCaching
public class EhCacheConfiguration {

	// ehcache 主要的管理器
	// @Bean(name = "appEhCacheCacheManager")
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
		return new EhCacheCacheManager(bean.getObject());
	}

	// 据shared与否的设置,Spring分别通过CacheManager.create()或new
	// CacheManager()方式来创建一个ehcache基地.
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}

	@Bean(name = "myKey")
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				if (objects != null && objects.length > 0) {
					for (Object obj : objects) {
						if (obj != null) {
							sb.append(obj.toString());
						}
					}
				}
				return sb.toString();
			}
		};

	}
}
