package com.psw.chating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication 어노테이션이란?
// 3가지 기능을 제공해준다
// 1) @EnableAutoConfiguration : Spring Boot의 자동화 기능(Spring 설정)을 활성화시켜줌
// 2) @ComponentScan : 패키지내 application 컴포넌트가 어디에 위치해있는지 검사함 (=bean검색)
// 3) @Configuration : bean에 대해서  Context에 추가하거나 특정 클래스를 참조해올 수 있음
// 스프링 : context.xml에서 component-scan 속성과 함꼐 scan할 패키지명을 적어줘야했지만
// @SpringBootApplication 어노테이션을 사용하면 : component-scan 때문에 xml을 사용할 일이 없어짐
@SpringBootApplication
public class Chat2Application {

	public static void main(String[] args) {
		SpringApplication.run(Chat2Application.class, args);
	}

}
