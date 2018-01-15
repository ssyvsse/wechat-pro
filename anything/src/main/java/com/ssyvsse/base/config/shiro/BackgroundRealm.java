package com.ssyvsse.base.config.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ssyvsse.base.constants.SessionConstants;
import com.ssyvsse.base.entity.Resource;
import com.ssyvsse.base.entity.Role;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.util.MD5Utils;
import com.ssyvsse.util.SpringContextUtil;

/**
 * @author llb
 *
 * @Date 2018年1月6日
 */
@Component
public class BackgroundRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	public BackgroundRealm() {
		super(new AllowAllCredentialsMatcher());
		setAuthenticationTokenClass(UsernamePasswordToken.class);
		// 暂时禁用Cache
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

		if (principals.getPrimaryPrincipal() instanceof User) {
			User user = (User) principals.getPrimaryPrincipal();
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			User dbUser = userService.getUserDao().findByUserName(user.getUserName());
			Set<String> shiroPermissions = new HashSet<String>();
			Set<String> roleSet = new HashSet<String>();
			Set<Role> roles = dbUser.getRoles();
			for (Role role : roles) {
				Set<Resource> resources = role.getResources();
				for (Resource resource : resources) {
					shiroPermissions.add(resource.getSourceKey());
				}
				roleSet.add(role.getRoleKey());
			}
			authorizationInfo.setRoles(roleSet);
			authorizationInfo.setStringPermissions(shiroPermissions);
			return authorizationInfo;
		} else {
			return null;
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();
		User user = userService.getUserDao().findByUserName(username);
		String password = new String((char[]) token.getCredentials());

		if (user == null) {
			throw new UnknownAccountException("帐号不正确");
		}

		if (!"background".equals(user.getLoginType())) {
			throw new UnknownAccountException("帐号不正确");
		}

		if (user.getLocked() == 1) {
			throw new LockedAccountException("帐号已被锁定,请联系管理员");
		}

		if (!user.getPassword().equals(MD5Utils.md5(password))) {
			throw new IncorrectCredentialsException("密码不正确");
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());

		return info;
	}

	@Override
	public String getName() {
		return "backgroundRealm";
	}

}
