package com.ssyvsse.base.controller.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.ssyvsse.util.DateUtils;

/**
 * 基于TCP协议的Socket通信，服务端
 * 
 * @author llb
 *
 * @Date 2018年1月23日
 */
public class SocketServer {

	/**
	 * 1、创建一个服务器端Socket，即ServerSocket
	 */
	private ServerSocket serverSocket = null;

	private int port;

	private Socket socket;

	private InputStream is = null;
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private OutputStream os = null;
	private PrintWriter pw = null;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public SocketServer(int port) {
		this.port = port;
	}

	/**
	 * 2、指定绑定的端口
	 * 
	 * @throws IOException
	 */
	public void initSocket() throws IOException {
		serverSocket = new ServerSocket(port);
	}

	/**
	 * 3、调用accept()方法开始监听，等待客户端的连接
	 * 
	 * @return
	 * @throws IOException
	 */
	public Socket accept() throws IOException {
		socket = serverSocket.accept();
		System.out.println("开始接收信息");
		return socket;
	}

	/**
	 * 初始化输入流
	 * 
	 * @return 返回缓冲流,用于读取数据
	 * @throws IOException
	 */
	public BufferedReader initResources() throws IOException {
		is = socket.getInputStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		System.out.println("输入流初始化完成");
		return br;
	}

	/**
	 * 获取输出流
	 * 
	 * @return
	 * @throws IOException
	 */
	public PrintWriter getPrintWriter() throws IOException {
		os = socket.getOutputStream();
		pw = new PrintWriter(os);
		System.out.println("成功获取输出流");
		return pw;
	}

	/**
	 * 关闭输入流
	 * 
	 * @throws IOException
	 */
	public void closeSocket() throws IOException {
		socket.shutdownInput();
	}

	/**
	 * 停止资源
	 * 
	 * @throws IOException
	 */
	public void stopResources() throws IOException {
		if (pw != null) {
			pw.close();
		}
		if (os != null) {
			os.close();
		}
		if (br != null) {
			br.close();
		}
		if (isr != null) {
			isr.close();
		}
		if (is != null) {
			is.close();
		}
		if (socket != null) {
			if (!socket.isClosed()) {
				socket.close();
			}
		}
		if (serverSocket != null) {
			if (!serverSocket.isClosed()) {
				serverSocket.close();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		SocketServer server = new SocketServer(10086);
		server.initSocket();
		Socket socket =server.accept();
		BufferedReader br = server.initResources();
		String line = null;
		while (!(line = br.readLine()).equals("stopSocket")) {
			System.out.println(socket.getRemoteSocketAddress()+":"+DateUtils.getNow()+"\n"+line);
		}
		server.stopResources();
	}
}
