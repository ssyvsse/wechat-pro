package com.ssyvsse.configuration;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午7:34:22 
 */
@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ServletContext servletContext;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			System.out.println("=======================================");
			System.out.println("=========spring 初始化完成 =============");
			System.out.println("=========spring 初始化完成 =============");
			System.out.println("=======================================");
			// 注入版本号
			servletContext.setAttribute("version", (int) (Math.random() * 1000000));
			// 注入友情链接
		}
	}
}
