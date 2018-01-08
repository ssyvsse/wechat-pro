package com.ssyvsse.base.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author llb
 *
 * @Date 2018年1月8日 下午9:36:34 
 */
@Controller
@RequestMapping("/admin/webSocket")
public class WebSocketChat {

	@GetMapping("/chat")
	public String chatFrame(){
		return "admin/chat";
	}
}
