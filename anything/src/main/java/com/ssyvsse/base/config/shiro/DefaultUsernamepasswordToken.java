package com.ssyvsse.base.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author llb
 *
 * @Date 2018年1月6日
 */
public class DefaultUsernamepasswordToken extends UsernamePasswordToken {
	
	private static final long serialVersionUID = 1L;
	
	private String loginType;

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public DefaultUsernamepasswordToken(final String username, final String password, String loginType) {
		super(username, password);
		this.loginType = loginType;
	}

	public DefaultUsernamepasswordToken(final String username, final String password) {
		super(username, password);

	}

	public DefaultUsernamepasswordToken(final String username, final String password, String loginType, boolean is) {
		super(username, password, is);
		this.loginType = loginType;
	}

	public DefaultUsernamepasswordToken() {

	}
}
