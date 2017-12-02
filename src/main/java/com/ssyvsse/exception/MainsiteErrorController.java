package com.ssyvsse.exception;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author llb
 *
 * @Date 2017年11月28日 下午8:53:21 
 */
@Controller
public class MainsiteErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	@RequestMapping(value = ERROR_PATH)
	public String handleError() {
		return "error/404";
	}


}
