package com.ssyvsse.base.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.entity.Log;
import com.ssyvsse.base.service.LogService;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午6:44:56 
 */

@RequestMapping("/admin/log")
@Controller
public class LogController {

	@Autowired
	private LogService logService;
	
	@GetMapping("/index")
	public String index(){
		return "admin/log/index";
	}
	
	@PostMapping("list")
	@ResponseBody
	public List<Log> list(){
		return logService.getLogMapper().findAll();
	}
	
}
