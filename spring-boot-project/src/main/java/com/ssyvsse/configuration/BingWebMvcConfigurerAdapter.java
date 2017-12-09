package com.ssyvsse.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ssyvsse.aspect.interceptor.UrlInterceptor;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
@Configuration
public class BingWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	@Bean
	public UrlInterceptor urlInterceptor() {
		return new UrlInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor()).addPathPatterns("/**")
		.excludePathPatterns("/404", "/500", "/mobile/404",
				"/mobile/500", "/error");
	}
}
