package com.ssyvsse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author llb
 *
 * @Date 2017年11月28日 下午8:46:29 
 */
@Controller
public class ErrControllerTest {

	/**
	 * 测试505
	 * @return
	 */
	@RequestMapping("/ee")
	public String err(){
		throw new RuntimeException();
	}

	@RequestMapping("/abc")
	public String notFound(){
		return "sdfsdfsfsdfsdfsdfds";
	}
	
}
