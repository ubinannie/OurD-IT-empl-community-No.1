package com.psw.chating.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Profile : ("로컬") 환경일 경우 내장 레디스가 실행됨을 처리하는 곳 
// ApplicationContext에 등록될 Bean 클래스를 설정한 Profile에 따라 선별적으로 등록할 수 있음 -> yml과 동일하게 
// @Slf4j
@Profile("local")
// @Configuration : 한개 이상의 Bean을 등록하고 있음을 명시
@Configuration
public class EmbeddedRedisConfig {
	
	// 어노테이션 할 때 주의 : 롬북 / spring내장 -> 선택 -> bean 어노테이션 사용한거임
	// @Value : Enviroment로 접근하지 않고 접근 할 수 있음
	// 프로퍼티값을 가져올 수 있음 
	// 포트 번호 가져오기 
	@Value("${spring.redis.port}")
	private int redisPort;
	
	// redis.embedded.RedisServer : 내장 레디스 
	private RedisServer redisServer;
	
	// 생성자가 호출 되었을 때 -> 빈은 초기화 전
	// @PostConstruct : 초기화 메소드에서 사용
	// @PostConstruct : 하지만 @PostConstruct을 사용 -> 빈이 초기화 됨과 동시에 의존성을 확인할 수 있음
	// bean이 여러번 초기화 되는 것을 방지
	@PostConstruct
	public void redisServer() {
		// 내장 레디스의 내 포트번호를 저장하고
		redisServer=new RedisServer(redisPort);
		// 시작한다
		redisServer.start();
	}
	
	// @PreDestroy : 마지막 소멸 메소드에서 사용
	// close()하기 직전에 실행 
	@PreDestroy
	public void stopRedis() {
		// 포트번호가 없다면
		if(redisServer!=null) {
			// 실행을 멈춰줘
			redisServer.stop();
		}
	}

}









