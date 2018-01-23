package com.ssyvsse.base.controller.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author llb
 *
 * @Date 2018年1月23日
 */
public class SocketClient {

	/**
	 * 1、创建客户端Socket
	 */
	private Socket socket = null;

	private int port;

	private String host;

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

	public SocketClient() {

	}

	public SocketClient(String host, int port) {
		this.port = port;
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 2、指定服务器地址和端口
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void initSocket() throws UnknownHostException, IOException {
		socket = new Socket(host, 10086);
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
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		SocketClient client = new SocketClient("localhost", 10086);
		client.initSocket();
		PrintWriter pw = client.getPrintWriter();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while(!"over".equals(line=br.readLine())) {
			pw.println(line);
			pw.flush();
		}
		client.stopResources();
	}
}
