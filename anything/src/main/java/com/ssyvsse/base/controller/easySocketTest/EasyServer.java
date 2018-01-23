package com.ssyvsse.base.controller.easySocketTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author llb
 *
 * @Date 2018年1月23日 下午10:17:44
 */
public class EasyServer {
	
	public static int count = 0;
	
	public static void main(String[] args) throws Exception { // 将异常全部抛出
		ServerSocket server = new ServerSocket(10000); // 用serversocket来启动服务器，并指定端口号
		System.out.println("服务器启动。。。");

		while (true) {
			Socket socket = server.accept(); // 获取客户端的socket信息
			MyThread mythread = new MyThread(socket);
			mythread.start();
			count++;
			System.out.println("客户端数量为：" + count);
		}
	}
}
