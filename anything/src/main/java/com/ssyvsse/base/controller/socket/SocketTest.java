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
		String hostName = address.getHostName();
		String ip = address.getHostAddress();// 获取IP地址
		//byte[] bytes = address.getAddress();// 获取字节数组形式的IP地址,以点分隔的四部分
		System.out.println("计算机名:"+hostName+",ip="+ip);

		// 创建一个URL的实例
		URL baidu = null;
		URL url = null;
		try {
			baidu = new URL("https://www.2349m.com");
			url = new URL(baidu, "/dataCenter/newly?typeid=38&token=pCiY2EE40xRWZrEX");
			System.out.println("协议protocol:"+url.getProtocol()+",主机host:"+url.getHost()+",端口:"+url.getPort());//如果没有指定端口号，根据协议不同使用默认端口 此时getPort()方法的返回值为-1
			System.out.println("文件路径:"+url.getPath()+",路径+参数:"+url.getFile());
			System.out.println("相对路径:"+url.getRef()+",查询字符串:"+url.getQuery());
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