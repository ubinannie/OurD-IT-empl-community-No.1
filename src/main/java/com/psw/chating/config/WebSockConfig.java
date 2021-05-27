package com.psw.chating.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
// @EnableWebSocketMessageBroker : WebSocketMessageBrokerConfigurer을 상속 
// configureMessageBroker을 구현함 
// pub/sub 메시징을 구현 -> 요청의 prefix는 /pub로 시작하도록 설정 -> 메세지를 구독하는 요청의 prefix는 /sub로 시작하도록 설정
// stomp websocket의 연결 endpoint -> /ws-stomp로 설정
// 따라서 접속 주소 : ws://localhost:8080/ws-stomp
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").withSockJS();
    }
}
