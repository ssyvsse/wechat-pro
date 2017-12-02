package com.ssyvsse.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理类
 * 
 * @author llb
 *
 * @Date 2017年11月28日 下午8:36:20
 */
@ControllerAdvice
public class ErrorExceptionHandler {
	private static final Logger logger = Logger.getLogger(ErrorExceptionHandler.class);

	/**
	 * 统一异常处理
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(RuntimeException exception) {
		logger.info("自定义异常处理-RuntimeException" + exception.getMessage());
		ModelAndView m = new ModelAndView();
		m.setViewName("error/505");
		return m;
	}

	/**
	 * 统一异常处理
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(Exception exception) {
		logger.info("自定义异常处理-Exception" + exception.getMessage());
		exception.printStackTrace();
		ModelAndView m = new ModelAndView();
		m.setViewName("error/505");
		return m;
	}
}
