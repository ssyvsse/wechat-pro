package com.ssyvsse.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssyvsse.wechat.config.ApiConfigKit;
import com.ssyvsse.wechat.service.CoreService;
import com.ssyvsse.wechat.utils.SignatureCheckKit;
import com.ssyvsse.wechat.utils.StrKit;

/**
 * 微信接入、微信授权登陆
 * 
 * @author llb
 *
 * @Date 2017年11月29日 下午8:51:12
 */
@RestController
@RequestMapping("/wechat")
public class WechatController extends BaseWechatController {
	private Logger log = Logger.getLogger(WechatController.class);

	@Autowired
	private CoreService coreService;
	/**
	 * 微信公众号服务器调用唯一入口，即在开发者中心输入的URL必须要指向此action
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "entry", method = RequestMethod.GET)
	public void index(HttpServletResponse response, HttpServletRequest request) {
		ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
		if (isConfigServerRequest(request)) {
			configServer(request, response);
			return;
		}
		if (!ApiConfigKit.isDevMode()) {
			// 签名检测
			if (!checkSignature(request, response)) {
				try {
					response.getWriter().print("签名验证失败，请确定是微信服务器在发送消息过来");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	/**
	 * 是否为开发者中心保存服务器配置的请求
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean isConfigServerRequest(HttpServletRequest request) {
		return StrKit.notBlank(request.getParameter("echostr"));
	}

	/**
	 * 配置开发者中心微信服务器所需的url与token
	 * 
	 * @param request
	 * @param response
	 */
	public void configServer(HttpServletRequest request, HttpServletResponse response) {
		// 通过echostr判断请求是否为配置微信服务器回调函数所需的url与token
		System.out.println(request.getRequestURL().toString());
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		boolean isOk = SignatureCheckKit.ME.checkSignature(signature, timestamp, nonce);
		if (isOk) {
			try {
				response.getWriter().write(echostr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			log.error("验证失败:configServer");
		}
	}

	/**
	 * 检测签名
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean checkSignature(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (StrKit.isBlank(signature) || StrKit.isBlank(timestamp) || StrKit.isBlank(nonce)) {
			try {
				response.getWriter().print("check signature failure");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		if (SignatureCheckKit.ME.checkSignature(signature, timestamp, nonce)) {
			return true;
		} else {

			log.error("check signature failure: " + " signature = " + signature + " timestamp = " + timestamp
					+ " nonce = " + nonce);
			return false;
		}

	}

	// 调用核心业务类接收消息、处理消息跟推送消息
	@RequestMapping(value = "entry", method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response) {
		coreService.processRequest(request, response);
	}
}
