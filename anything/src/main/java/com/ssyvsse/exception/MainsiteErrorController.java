package com.ssyvsse.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午9:30:30 
 */
@Controller
public class MainsiteErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH)
	public String handleError(Device device,HttpServletResponse response) {
		response.setStatus(404);
		if (device.isMobile()) {// 手机端
			return "/mobile/404";
		}
		return "/err/404";
	}
}
