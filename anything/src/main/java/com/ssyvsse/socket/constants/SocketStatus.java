package com.ssyvsse.socket.constants;

import java.util.ArrayList;
import java.util.List;

import com.ssyvsse.socket.pojo.Message;

/**
 * @author llb
 *
 * @Date 2018年1月25日
 */
public class SocketStatus {

	/**
	 * socket read switch
	 */
	public static boolean isRead = false;

	/**
	 * socket write switch
	 */
	public static boolean isWrite = false;
	
	/**
	 * 消息list
	 */
	public final static List<Message> msgList = new ArrayList<Message>();

}
