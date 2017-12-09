package com.ssyvsse.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author llb
 * @Configuration @EnableWebSocketMessageBroker//1 @author llb
 *
 * @Date 2017年12月8日 下午16:15:35
 */
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/*
	 * 1.@EnableWebSocketMessageBroker注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。
	 * 2.registerStompEndpoints方法表示注册STOMP协议的节点，并指定映射的URL。
	 * 3.stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();
	 * 这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。
	 * 4.configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
	 */

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpointSang").withSockJS();// 2
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");// 3

	}

}
