package com.ssyvsse.socket.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssyvsse.socket.constants.SocketStatus;
import com.ssyvsse.socket.pojo.Message;
import com.ssyvsse.util.IpUtils;

/**
 * @author llb
 *
 * @Date 2018年1月25日
 */
public class SocketReadThread implements Runnable {

	private Logger logger = LoggerFactory.getLogger(SocketReadThread.class);

	private Socket socket;

	public SocketReadThread() {

	}

	public SocketReadThread(Socket socket) {
		this.socket = socket;
	}

	private BufferedReader br;

	/**
	 * 开启缓冲流进行读取数据
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedReader startRead() throws IOException {
		if (socket != null) {
			throw new IOException("socket is null");
		}
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return br;
	}

	/**
	 * 关闭缓冲流
	 * 
	 * @throws IOException
	 */
	public void stopRead() throws IOException {
		if (br != null) {
			br.close();
		}
	}

	@Override
	public void run() {
		logger.info("SocketStatus.isRead" + SocketStatus.isRead);
		while (SocketStatus.isRead) {
			try {
				Message msg = new Message();
				String line = br.readLine();
				msg.setDate(new Date());
				msg.setFromWhere(socket.getRemoteSocketAddress().toString());
				msg.setText(line);
				msg.setToName(IpUtils.getHostIp());
				System.out.println(msg);
				SocketStatus.msgList.add(msg);
				if(SocketStatus.msgList.size()>100) {
					
				}
			} catch (IOException e) {
				logger.error("wrong appear:"+e.getMessage());
			}
		}
	}

	/**
	 * 关闭socket
	 * @throws IOException
	 */
	public void closeSocket() throws IOException {
		if(socket!=null) {
			socket.close();
		}
	}
	
}
