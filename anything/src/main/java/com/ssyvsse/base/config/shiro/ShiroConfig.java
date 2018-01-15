package com.ssyvsse.base.config.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

/**
 * @author llb
 *
 * @Date 2018年1月6日
 */
@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public List<Realm> realm() {
		List<Realm> list = new ArrayList<>();
		list.add(new BackgroundRealm());
		return list;
	}

	@Bean(name = "defineModularRealmAuthenticator")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public DefualtModularRealm defualtModularRealm() {
		DefualtModularRealm defualtModularRealm = new DefualtModularRealm();
		return defualtModularRealm;
	}

	/**
	 * 用户授权信息Cache
	 */
	@Bean(name = "shiroCacheManager")
	@ConditionalOnMissingBean
	public CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@Bean(name = "securityManager")
	@ConditionalOnMissingBean
	public DefaultSecurityManager securityManager() {
		DefaultSecurityManager sm = new DefaultWebSecurityManager();
		sm.setCacheManager(cacheManager());
		sm.setRememberMeManager(rememberMeManager());
		return sm;
	}
	
	/**
     * cookie对象;
     * @return
     */
	@Bean(name = "rememberMeCookie")
	@ConditionalOnMissingBean
    public SimpleCookie rememberMeCookie(){
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(2592000);
       return simpleCookie;
    }
	
    /**
     * cookie管理对象;记住我功能
     * @return
     */
    @Bean(name = "rememberMeManager")
	@ConditionalOnMissingBean
    public CookieRememberMeManager rememberMeManager(){
       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
       cookieRememberMeManager.setCookie(rememberMeCookie());
       //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
       cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
       return cookieRememberMeManager;
    }
    
	@Bean(name = "shiroFilter")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, List<Realm> realm,
			DefualtModularRealm defualtModularRealm) {
		
		securityManager.setRealms(realm);
		securityManager.setAuthenticator(defualtModularRealm);
		
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/admin/login.html");
		shiroFilter.setSuccessUrl("/admin/index.html");
		shiroFilter.setUnauthorizedUrl("/previlige/no");
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/assets/**", "anon");
		filterChainDefinitionMap.put("/admin/login.html", "anon");
		filterChainDefinitionMap.put("/admin/index.html", "authc");
		filterChainDefinitionMap.put("/admin/index/webSocket/chat", "authc");
		filterChainDefinitionMap.put("/admin/*", "authc");
		//filterChainDefinitionMap.put("/admin/**", "authc");
		/*List<com.cp.base.entity.Resource> list = resourceService.findAll();
		for (com.cp.base.entity.Resource resource : list) {
//			System.out.println(resource.getSourceUrl()+ "perms[" + resource.getSourceKey() + "]");
			filterChainDefinitionMap.put(resource.getSourceUrl(), "perms[" + resource.getSourceKey() + "]");
		}*/
		
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		UserFilter adminAuthc=new UserFilter();
		adminAuthc.setLoginUrl("/");
		
		
		Map<String,Filter> map=new HashMap<String, Filter>();
		map.put("customerFilter", adminAuthc);
		
		shiroFilter.setFilters(map);
		
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
	
	
}
