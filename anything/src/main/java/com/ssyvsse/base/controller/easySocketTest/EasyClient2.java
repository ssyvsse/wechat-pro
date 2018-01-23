package com.ssyvsse.base.controller.easySocketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author llb
 *
 * @Date 2018年1月23日 下午10:18:33
 */
public class EasyClient2 {
	public static void main(String[] args) {
		new EasyClient2().init();
	}
	
	public void init(){
		Socket socket = null;
		try {
			socket = new Socket("localhost",10086);
			new Thread(new ClientRead(socket)).start();
			new Thread(new ClientWrite(socket)).start();
		} catch (Exception e) {
			System.out.println(1);
			e.printStackTrace();
		}
	}

}


class ClientRead implements Runnable{

	private Socket client;
	
	public ClientRead(Socket socket){
		this.client = socket;
	}
	
	
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			while(true){
				br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				System.out.println("服务端说:"+br.readLine());
			}
		} catch (Exception e) {
			System.out.println("2");
			e.printStackTrace();
		} finally{
			if(client!=null){
				client = null;
			}
		}
	}
	
}

class ClientWrite implements Runnable{

	private Socket client;
	
	
	public ClientWrite(Socket socket){
		this.client = socket;
	}
	
	@Override
	public void run() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(client.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(true){
				String line = br.readLine();
				pw.println(line);
				pw.flush();
			}
		} catch (Exception e) {
			System.out.println(3);
			e.printStackTrace();
		} finally {
			if(client != null){    
				System.out.println("client not null");
                client = null; 
                return;
            }
			System.out.println("client is null");
		}
	}
	
}
