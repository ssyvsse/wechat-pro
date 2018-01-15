package com.ssyvsse.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

/**
 * @author llb
 *
 * @Date 2018年1月5日
 */
public class MD5Utils {
	/**
	 * 对字符串进行Md5加密
	 * 
	 * @param input
	 *            原文
	 * @return md5后的密文
	 */
	public static String md5(String password) {
		byte[] code = null;
		try {
			code = MessageDigest.getInstance("md5").digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			code = password.getBytes();
		}
		BigInteger bi = new BigInteger(code);
		return bi.abs().toString(32).toUpperCase();
	}

	/**
	 * 对字符串进行Md5加密
	 * 
	 * @param input
	 *            原文
	 * @param salt
	 *            随机数
	 * @return string
	 */
	public static String md5WithSalt(String password, String salt) {
		if (StringUtils.isEmpty(salt)) {
			salt = "";
		}
		return md5(password + salt);
	}
	
	public static void main(String[] args) {
		System.out.println(md5("p3wgn"));;
		System.out.println(md5WithSalt("p3wgn","4564sdf5"));;
	}
}
