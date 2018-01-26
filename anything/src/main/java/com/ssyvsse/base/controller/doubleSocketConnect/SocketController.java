package com.ssyvsse.base.controller.doubleSocketConnect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;

/**
 * @author llb
 *
 * @Date 2018年1月24日
 */

@RequestMapping("/c")
@Controller
public class SocketController {

	/**
	 * 主页
	 * 
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return null;
	}

	/**
	 * 设置socket端口号
	 * 
	 * @return
	 */
	@PostMapping("/setPort")
	@ResponseBody
	public JsonResult setPort(Integer port) {
		return null;
	}

	/**
	 * 开启读取数据的线程
	 * 
	 * @return
	 */
	@GetMapping("/startRead")
	@ResponseBody
	public JsonResult startReading() {
		return null;
	}

	/**
	 * 开启写数据线程
	 * 
	 * @return
	 */
	@GetMapping("/startWrite")
	@ResponseBody
	public JsonResult startWriting() {
		return null;
	}

	/**
	 * 发送消息
	 * 
	 * @param content
	 * @return
	 */
	@PostMapping("/sendMsg")
	@ResponseBody
	public JsonResult sendMsg(String content) {
		return null;
	}

	/**
	 * 接收消息
	 * 
	 * @return
	 */
	@PostMapping("/receiveMsg")
	@ResponseBody
	public JsonResult receiveMsg() {
		return null;
	}
}
