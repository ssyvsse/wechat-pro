package com.ssyvsse.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author llb
 *
 * @Date 2017年12月10日 下午3:07:25 
 */
@Controller
public class WechatIndexController {

	@RequestMapping(value={"/","/welcome"})
	public String index(){
		return "welcome";
	}
	
}
