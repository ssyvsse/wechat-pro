package com.ssyvsse.base.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.controller.BaseController;
import com.ssyvsse.base.entity.Log;
import com.ssyvsse.base.service.LogService;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午6:44:56 
 */

@RequestMapping("/admin/log")
@Controller
public class LogController extends BaseController{

	@Autowired
	private LogService logService;
	
	@GetMapping("/index")
	public String index(){
		return "admin/log/index";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public Page<Log> list(String searchText,String begin,String end){
		return logService.selectAllLog(searchText, begin, end, getPageRequest());
	}
	
}
