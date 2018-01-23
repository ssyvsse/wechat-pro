package com.ssyvsse.base.controller.doubleSocketConnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author llb
 *
 * @Date 2018年1月23日 下午10:36:27
 */
public class Server {
	public static final int PORT = 10086;// 监听的端口号

	public static void main(String[] args) {
		System.out.println("sever begins");
		Server server = new Server();
		server.init();
	}

	private int count = 0;
	
	public void init() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			while (true) {
				Socket client = serverSocket.accept();
				// 一个客户端连接就开两个线程分别处理读和写
				new Thread(new ReadHandlerThread(client)).start();
				new Thread(new WriteHandlerThread(client)).start();
				count ++;
				System.out.println(count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}


/*   
 *处理读操作的线程    
 */    
class ReadHandlerThread implements Runnable{    
    private Socket client=null;    
      
    public ReadHandlerThread(Socket client) {    
        this.client = client;    
    }    
    
    @Override    
    public void run() {    
        BufferedReader in = null;  
        try{    
        	in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
            while(true){    
                //读取客户端数据      
                System.out.println("客户端说:" + in.readLine());     
            }    
        }catch(Exception e){    
            e.printStackTrace();    
        }finally{    
            if(client != null){    
                client = null;    
            }    
        }    
    }    
}    
    
/*   
 * 处理写操作的线程   
 */    
class WriteHandlerThread implements Runnable{    
    private Socket client;    
    
    public WriteHandlerThread(Socket client) {    
        this.client = client;    
    }    
    
    @Override    
    public void run() {    
        PrintWriter out=null;  
        try {  
            out = new PrintWriter(client.getOutputStream());  
        } catch (IOException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        }    
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//从控制台获取输入的内容   
        try{    
            while(true){    
                //向客户端回复信息      
                out.println(reader.readLine());  
                out.flush();    
            }    
        }catch(Exception e){    
            e.printStackTrace();    
        }finally{    
            if(client != null){    
                client = null;    
            }    
        }    
    }    
}   
