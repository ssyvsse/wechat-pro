package com.ssyvsse.base.config.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author llb
 *
 * @Date 2018年1月6日
 */
public class DefualtModularRealm extends ModularRealmAuthenticator {

	private Map<String, Object> definedRealms;

	@Autowired
	private BackgroundRealm backgroundRealm;
	
	@Override
	protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
		return super.doMultiRealmAuthentication(realms, token);
	}

	/**
	 * 调用单个realm执行操作
	 */
	@Override
	protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken token) {
		// 如果该realms不支持(不能验证)当前token
		if (!realm.supports(token)) {
			throw new ShiroException("token错误!");
		}
		AuthenticationInfo info = null;
		try {
			info = realm.getAuthenticationInfo(token);
			if (info == null) {
				throw new ShiroException("token不存在!");
			}
		} catch (Exception e) {
			throw new ShiroException("用户名或者密码错误!");
		}
		return info;
	}

	/**
	 * 判断登录类型执行操作
	 */
	@Override
	protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
			throws AuthenticationException {

		// 强制转换回自定义的DefaultUsernamepasswordToken
		DefaultUsernamepasswordToken token = (DefaultUsernamepasswordToken) authenticationToken;
		// 登录类型
		String loginType = token.getLoginType();
		// 所有Realm
		Collection<Realm> realms = new ArrayList<>();
		realms.add(backgroundRealm); 
		Realm realm = null;
		if("background".equals(loginType)) {
			realm = backgroundRealm;
		}
		return this.doSingleRealmAuthentication(realm, authenticationToken);
	}

	/**
	 * 判断realm是否为空
	 */
	@Override
	protected void assertRealmsConfigured() throws IllegalStateException {
		System.out.println("realm空");
		this.definedRealms = this.getDefinedRealms();
		if (CollectionUtils.isEmpty(this.definedRealms)) {
			throw new ShiroException("值传递错误!");
		}
	}

	public Map<String, Object> getDefinedRealms() {
		return this.definedRealms;
	}

	public void setDefinedRealms(Map<String, Object> definedRealms) {
		this.definedRealms = definedRealms;
	}
}
