package com.ssyvsse.configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author llb
 *
 * @Date 2017年12月6日 下午9:46:52
 */
public class BingWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor();
		super.addInterceptors(registry);
	}
}
