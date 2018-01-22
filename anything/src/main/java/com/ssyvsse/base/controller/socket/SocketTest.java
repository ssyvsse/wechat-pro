package com.ssyvsse.base.controller.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * @author llb
 *
 * @Date 2018年1月22日 下午11:26:10
 */
public class SocketTest {
	public static void main(String[] args) {
		// 获取本机的InetAddress实例
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			System.out.println(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String hostName = address.getHostName();// 获取计算机名
		String ip = address.getHostAddress();// 获取IP地址
		System.out.println(hostName + "-" + ip);
		byte[] bytes = address.getAddress();// 获取字节数组形式的IP地址,以点分隔的四部分
		System.out.println(new String(bytes));

		// 创建一个URL的实例
		URL baidu = null;
		URL url = null;
		try {
			baidu = new URL("https://www.2349m.com");
			url = new URL(baidu, "/dataCenter/newly?typeid=38&token=pi");
			System.out.println(url.getProtocol());// 获取协议
			System.out.println(url.getHost());// 获取主机
			System.out.println(url.getPort());// 如果没有指定端口号，根据协议不同使用默认端口。此时getPort()方法的返回值为
												// -1
			System.out.println(url.getPath());// 获取文件路径
			System.out.println(url.getFile());// 文件名，包括文件路径+参数
			System.out.println(url.getRef());// 相对路径，就是锚点，即#号后面的内容
			System.out.println(url.getQuery());// 查询字符串，即参数
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = url.openStream();// 通过openStream方法获取资源的字节输入流
			isr = new InputStreamReader(is, "UTF-8");// 将字节输入流转换为字符输入流,如果不指定编码，中文可能会出现乱码
			br = new BufferedReader(isr);// 为字符输入流添加缓冲，提高读取效率
			String data = br.readLine();
			while (data != null) {
				System.out.println(data);// 输出数据
				data = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// https://www.cnblogs.com/rocomp/p/4790340.html
	}
}