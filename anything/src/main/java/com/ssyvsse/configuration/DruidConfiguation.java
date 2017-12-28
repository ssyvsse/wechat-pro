package com.ssyvsse.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午9:17:45
 */
@Configuration
public class DruidConfiguation {
	
	@Value("${druid.username}")
	private String loginUsername;

	@Value("${druid.password}")
	private String loginPassword;

	@Value("${druid.allow}")
	private String allow;

	@Value("${druid.deny}")
	private String deny;

	@Bean
	public ServletRegistrationBean statViewServle() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		// 白名单：
		servletRegistrationBean.addInitParameter("allow", allow);
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的即提示:Sorry, you are not
		// permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", deny);
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
		servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean statFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/druid/*");
		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
