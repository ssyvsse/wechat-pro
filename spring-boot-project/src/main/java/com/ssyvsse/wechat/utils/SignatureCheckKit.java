package com.ssyvsse.wechat.utils;

import java.util.Arrays;

import com.ssyvsse.wechat.config.ApiConfigKit;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class SignatureCheckKit {

	public static final SignatureCheckKit me = new SignatureCheckKit();

	/**
	 * @param signature
	 *            微信加密签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机字符串
	 * @return {boolean}
	 */
	public boolean checkSignature(String signature, String timestamp, String nonce) {
		String array[] = { ApiConfigKit.getApiConfig().getToken(), timestamp, nonce };
		Arrays.sort(array);
		String tempStr = new StringBuilder().append(array[0] + array[1] + array[2]).toString();
		tempStr = HashKit.sha1(tempStr);
		return tempStr.equalsIgnoreCase(signature);
	}

}
