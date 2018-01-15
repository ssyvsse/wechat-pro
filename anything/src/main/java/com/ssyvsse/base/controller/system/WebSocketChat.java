package com.ssyvsse.base.controller.system;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssyvsse.base.entity.User;

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
