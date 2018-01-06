package com.ssyvsse.base.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IUserService;

/**
 * @author llb
 *
 * @Date 2018年1月6日
 */
@Component
public class BackgroundRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(BackgroundRealm.class);
	
	@Autowired
	private IUserService userService;
	
	public BackgroundRealm() {
		super(new AllowAllCredentialsMatcher());
		setAuthenticationTokenClass(UsernamePasswordToken.class);
		// FIXME: 暂时禁用Cache
		setCachingEnabled(false);
		/*
		 * //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false；
		 * setAuthenticationCachingEnabled(true);
		 * //缓存AuthenticationInfo信息的缓存名称,即配置在ehcache.xml中的cache name
		 * setAuthenticationCacheName("resourceCache");
		 * //启用授权缓存，即缓存AuthorizationInfo信息，默认false；
		 * setAuthorizationCachingEnabled(true); //缓存AuthorizationInfo信息的缓存名称；
		 * setAuthorizationCacheName("resourceCache");
		 */
	}
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("backgroundRealm~~~~查询权限当中");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> per = new HashSet<>();
		authorizationInfo.setStringPermissions(per);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getUserDao().findByUserName(username);
		return null;
	}
	
	@Override
	public String getName() {
		return "backgroundRealm";
	}

}
