package com.ssyvsse.utils;

/**
 * @author llb
 *
 * @Date 2017年12月17日 下午9:14:10
 */
public class MyRandom {
	public static String randomCode(int Count) {
		String code = "";
		for (int i = 0; i < Count; i++) {
			code = code + ((int) (Math.random() * 10));
		}
		return code;
	}

	public static String randomUserName(int Count) {
		String userName = "";
		for (int i = 0; i < Count; i++) {
			userName = userName + ((char) ((int) (Math.random() * 26) + 97));
		}
		return userName;
	}

	public static void main(String[] args) {
		System.out.println(randomCode(6));
		// System.out.println(randomUserName(100));
	}
}
