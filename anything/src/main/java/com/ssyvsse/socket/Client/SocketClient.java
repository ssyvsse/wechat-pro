package com.ssyvsse.socket.Client;

import java.net.Socket;

/**
 * @author llb
 *
 * @Date 2018年1月25日
 */
public class SocketClient {

	private Socket socket;

	private String hostName;

	private int port;

	public SocketClient() {

	}

	public SocketClient(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
