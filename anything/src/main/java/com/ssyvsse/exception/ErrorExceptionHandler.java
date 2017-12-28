package com.ssyvsse.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午8:12:25
 */
@ControllerAdvice
public class ErrorExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);

	/**
	 * 统一异常处理
	 * 
	 * @param exception
	 *            exception
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(RuntimeException exception, Device device) {
		logger.info("自定义异常处理-RuntimeException " + exception.getMessage());
		exception.printStackTrace();
		ModelAndView m = new ModelAndView();
		if (device.isMobile()) {// 手机端
			m.setViewName("/mobile/500");
		} else {
			m.setViewName("/err/500");
		}

		return m;
	}

	/**
	 * 统一异常处理
	 * 
	 * @param exception
	 *            exception
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(Exception exception, Device device) {
		logger.info("自定义异常处理-Exception" + exception.getMessage());
		exception.printStackTrace();
		ModelAndView m = new ModelAndView();
		if (device.isMobile()) {// 手机端
			m.setViewName("/mobile/500");
		} else {
			m.setViewName("/err/500");
		}
		return m;
	}
}
