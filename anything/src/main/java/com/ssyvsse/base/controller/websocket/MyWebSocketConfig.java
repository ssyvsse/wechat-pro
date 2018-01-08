package com.ssyvsse.base.controller.websocket;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebScoket配置处理器
 * 
 * @author llb
 *
 * @Date 2018年1月8日 下午9:43:48 
 */
@Component
@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	private Logger logger = LoggerFactory.getLogger(MyWebSocketConfig.class);
	
	@Resource
	ChatWebSocketHandler chatWebSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		logger.info("handler: /ws /ws/sockjs");
		registry.addHandler(chatWebSocketHandler, "/ws").addInterceptors(new HandShake()).setAllowedOrigins("*");
		registry.addHandler(chatWebSocketHandler, "/ws/sockjs").addInterceptors(new HandShake()).setAllowedOrigins("*").withSockJS();
	}

}
