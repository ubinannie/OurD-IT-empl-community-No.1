package com.psw.chating.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 롬복을 사용해서 getter / setter를 자동으로 만들어주는 어노테이션 생성
@Getter
@Setter
@ToString
public class ChatMessage {

    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    
    
    
}