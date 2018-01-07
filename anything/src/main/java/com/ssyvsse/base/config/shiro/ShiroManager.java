package com.ssyvsse.base.config.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Shiro Config Manager.
 * 
 * @author llb
 *
 * @Date 2018年1月6日
 */
public class ShiroManager {

	/**
	 * 保证实现了Shiro内部lifecycle函数的bean执行
	 * 
	 * @ConditionalOnMissingBean的意思是如果存在指定name的bean，则该注解标注的bean不创建
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@ConditionalOnMissingBean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return new AuthorizationAttributeSourceAdvisor();
	}
}
