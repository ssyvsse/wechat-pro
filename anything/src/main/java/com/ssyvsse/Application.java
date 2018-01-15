package com.ssyvsse;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.ssyvsse.util.SpringContextUtil;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午7:31:33 
 */
@SpringBootApplication
//(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ServletComponentScan
@MapperScan({ "com.ssyvsse.dao", "com.ssyvsse.base.dao", "com.ssyvsse.wechat.dao" })
@PropertySource("classpath:${mybatis}.properties")
@Configuration
public class Application {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) throws Exception {
		ApplicationContext app = SpringApplication.run(Application.class, args);
		SpringContextUtil.setApplicationContext(app);
	}
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory createSqlSessionFactoryBean() throws Exception {
		// 设置datasource
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean(); 
		fb.setDataSource(dataSource); 
		// 设置typeAlias 包扫描路径
		fb.setTypeAliasesPackage("com.ssyvsse.pojo");

		// 配置mapper xml文件
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		fb.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		return fb.getObject();
	}
	
}
