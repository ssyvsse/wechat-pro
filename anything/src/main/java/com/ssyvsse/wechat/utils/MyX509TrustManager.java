package com.ssyvsse.wechat.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * @author llb
 *
 * @Date 2018年1月14日 上午9:09:11 
 */
public class MyX509TrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	
}
