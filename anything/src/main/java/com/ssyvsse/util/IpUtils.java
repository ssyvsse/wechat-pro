package com.ssyvsse.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.text.StrTokenizer;

/**
 * @author llb
 *
 * @Date 2018年1月7日 上午10:10:56
 */
public final class IpUtils {

	private final static String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	private final static Pattern pattern = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255 + "$");

	public static String getRemoteHost(HttpServletRequest request) {
		/*
		 * String ip = request.getHeader("x-forwarded-for"); if (ip == null ||
		 * ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
		 * request.getHeader("Proxy-Client-IP"); } if (ip == null || ip.length() == 0 ||
		 * "unknown".equalsIgnoreCase(ip)) { ip =
		 * request.getHeader("WL-Proxy-Client-IP"); } if (ip == null || ip.length() == 0
		 * || "unknown".equalsIgnoreCase(ip)) { ip = request.getRemoteAddr(); } return
		 * ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
		 */
		String ip = request.getHeader("x-forwarded-for");
		boolean found = false;
		if (ip != null || !"unknown".equalsIgnoreCase(ip)) {
			StrTokenizer tokenizer = new StrTokenizer(ip, ",");
			while (tokenizer.hasNext()) {
				ip = tokenizer.nextToken().trim();
				if (isIPv4Valid(ip) && !isIPv4Private(ip)) {
					found = true;
					break;
				}
				return ip;
			}
		}
		if (!found) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	private static boolean isIPv4Private(String ip) {
		long longIp = ipV4ToLong(ip);
		return (longIp >= ipV4ToLong("10.0.0.0") && longIp <= ipV4ToLong("10.255.255.255"))
				|| (longIp >= ipV4ToLong("172.16.0.0") && longIp <= ipV4ToLong("172.31.255.255"))
				|| longIp >= ipV4ToLong("192.168.0.0") && longIp <= ipV4ToLong("192.168.255.255");
	}

	private static boolean isIPv4Valid(String ip) {
		return pattern.matcher(ip).matches();
	}

	private static long ipV4ToLong(String ip) {
		String[] octets = ip.split("\\.");
		return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16)
				+ (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);
	}

	/**
	 * 获取本机ip地址
	 * 
	 * @return
	 */
	public static String getHostIp() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress() // loopback地址即本机地址，IPv4的loopback范围是127.0.0.0
																							// ~ 127.255.255.255
							&& ip.getHostAddress().indexOf(":") == -1) {
						System.out.println("本机的IP = " + ip.getHostAddress());
						return ip.getHostAddress();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
