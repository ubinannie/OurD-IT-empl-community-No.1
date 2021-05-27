package com.psw.chating.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// redis pub/sub 메세지를 처리하는 listener 설정
// @Configuration : 한개 이상의 Bean을 등록하고 있음을 명시
// @Configuration의 어노테이션은 환경구성을 돕는다 
// 예) @RedisConnectionFactory / @RedisTemplate
@Configuration
public class RedisConfig {
	
	@Bean
	// RedisMessageListenerContainer : Spring Data Redis에서 제공하는 클래스
	// Redis 채널로부터 메시지를 받는데 사용
	// 구독자들에게 메시지를 받는 역할 
	// RedisConnectionFactory : Configuration 중에 하나 -> 환경구성을 돕는 어노테이션
	public RedisMessageListenerContainer redisMessageListener(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		return container;
	}
	
	// 어플리케이션에서 사용할 redisTemplate 설정
	// RedisTemplate : Configuration 중에 하나 -> 환경구성을 돕는 어노테이션
	// RedisConnectionFactory & RedisTemplate의 차이점이 무엇일까?
	@Bean
	public RedisTemplate<String, Object>redisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate<String, Object>redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
	    return redisTemplate;
	}

}



