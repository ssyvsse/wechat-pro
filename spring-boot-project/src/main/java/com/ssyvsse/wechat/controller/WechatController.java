package com.ssyvsse.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Date 2017年12月9日
 */
@RestController
@RequestMapping("/wechat")
public class WechatController extends BaseWechatController {

	private Logger log = LoggerFactory.getLogger(WechatController.class);
	@Autowired
	private CoreService coreService;

	@RequestMapping(value = "entry", method = RequestMethod.GET)
	public void index(HttpServletResponse response, HttpServletRequest request) {
		ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
		// 如果是服务器配置请求，则配置服务器并返回
		if (isConfigServerRequest(request, response)) {
			configServer(request, response);
			return;
		}
		if (!ApiConfigKit.isDevMode()) {
			// 签名检测
			if (!checkSignature(request, response)) {
				try {
					response.getWriter().print("签名验证失败，请确定是微信服务器在发送消息过来");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 是否为开发者中心保存服务器配置的请求
	 */
	private boolean isConfigServerRequest(HttpServletRequest request, HttpServletResponse response) {
		return StrKit.notBlank(request.getParameter("echostr"));
	}

	/**
	 * 配置开发者中心微信服务器所需的 url 与 token
	 */
	public void configServer(HttpServletRequest request, HttpServletResponse response) {
		// 通过 echostr 判断请求是否为配置微信服务器回调所需的 url 与 token
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		boolean isOk = SignatureCheckKit.me.checkSignature(signature, timestamp, nonce);
		if (isOk) {
			try {
				response.getWriter().write(echostr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			log.error("验证失败：configServer");
	}

	/**
	 * 检测签名
	 */
	private boolean checkSignature(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (StrKit.isBlank(signature) || StrKit.isBlank(timestamp) || StrKit.isBlank(nonce)) {
			try {
				response.getWriter().print("check signature failure");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		if (SignatureCheckKit.me.checkSignature(signature, timestamp, nonce)) {
			return true;
		} else {
			log.error(
					"check signature failure: " + " signature = " + request.getParameter("signature") + " timestamp = "
							+ request.getParameter("timestamp") + " nonce = " + request.getParameter("nonce"));

			return false;
		}
	}

	// 调用核心业务类接收消息、处理消息跟推送消息
	@RequestMapping(value = "entry", method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response) {
		coreService.processRequest(request, response);
	}
}
