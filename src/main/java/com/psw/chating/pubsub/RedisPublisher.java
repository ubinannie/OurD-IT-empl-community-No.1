package com.psw.chating.pubsub;

import com.psw.chating.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;


// 이부분은 서비스 부분이라고 생각하면 편함
// @RequiredArgsConstructor : 의존성 주입
// final / @NonNull -> 인 필드 값만 파라미터로 받는 생성자를 만들어줌 
@RequiredArgsConstructor
@Service
public class RedisPublisher{
	
	private final RedisTemplate<String, Object>redisTemplate;
	
	public void publish(ChannelTopic topic, ChatMessage message) {
		redisTemplate.convertAndSend(topic.getTopic(),message);
	}

}
