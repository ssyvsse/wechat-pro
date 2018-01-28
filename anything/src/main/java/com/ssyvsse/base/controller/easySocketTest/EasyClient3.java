package com.ssyvsse.base.controller.easySocketTest;

import java.net.Socket;

/**
 * @author llb
 *
 * @Date 2018年1月23日 下午10:18:33
 */
public class EasyClient3 {
	public static void main(String[] args) {
		new EasyClient3().init();
	}
	
	public void init(){
		Socket socket = null;
		try {
			socket = new Socket("localhost",10086);
			System.out.println(socket.getLocalPort());
			new Thread(new ClientRead(socket)).start();
			new Thread(new ClientWrite(socket)).start();
		} catch (Exception e) {
			System.out.println(1);
			e.printStackTrace();
		}
	}
}
