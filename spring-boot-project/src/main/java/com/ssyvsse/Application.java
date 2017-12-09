package com.ssyvsse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author llb
 *
 * @Date 2017年11月27日 下午9:32:58
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ServletComponentScan
// @MapperScan({ "com.ssyvsse.dao", "com.ssyvsse.base.dao",
// "com.ssyvsse.wechat.dao" })
// @PropertySource("classpath:mybatis_test.properties")
@Configuration
public class Application {

	// @Autowired
	// private DataSource dataSource;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	/*
	 * @Bean(name = "sqlSessionFactory") public SqlSessionFactory
	 * createSqlSessionFactoryBean() throws Exception { SqlSessionFactoryBean fb
	 * = new SqlSessionFactoryBean(); // 设置datasource
	 * fb.setDataSource(dataSource); // 设置typeAlias 包扫描路径
	 * fb.setTypeAliasesPackage("com.ssyvsse.pojo");
	 * 
	 * // 配置mapper xml文件 PathMatchingResourcePatternResolver resolver = new
	 * PathMatchingResourcePatternResolver();
	 * fb.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
	 * return fb.getObject(); }
	 */
}
