package com.ssyvsse.base.controller.easySocketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author llb
 *
 * @Date 2018年1月23日 下午10:24:08
 */
public class MyThread extends Thread {
	Socket socket = null;

	public MyThread(Socket socket) throws Exception {
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		} // 启动缓冲区
			// out = new PrintWriter(client.getOutputStream());
		while (true) {
			String msg = null;
			try {
				msg = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			} // 将客户端发送来的信息存储在msg中
			System.out.println(socket.getRemoteSocketAddress()+":"+msg);
			if (msg.equals("shutdown")) { // 客户端如果发送的是shutdown，就关闭客户端
				EasyServer.count--;
				System.out.println("客户端数量为:"+EasyServer.count);
				break;
			}
		}
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 执行相应的关闭操作

	}
}
