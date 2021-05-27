package com.psw.chating.controller;

import com.psw.chating.model.ChatMessage;
import com.psw.chating.pubsub.RedisPublisher;
import com.psw.chating.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

// localhost:8080/chat/room
// WebSockChatHandler의 역할을 해주는 곳 
@RequiredArgsConstructor
@Controller
public class ChatController {

	private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;
    // private final SimpMessageSendingOperations messagingTemplate;

    // @MessageMapping : Websocket으로 들어오는 메시지 발행을 처리
    // 클라이언트 : prefix를 붙여서 /pub/chat.message로 발행 요청 -> 컨트롤러 -> 해당 메세지를 받아 처리함
    // 메세지 발행 -> /sub/chat/room/{roomId}로 메시지를 send
    // 클라이언트에서는 해당 ;주소를 (/sub/chat/room/{roomId}) 구독 하고 있다가 메시지가 전달되면 화면에 출력
    // /sub/chat/room/{roomId} : 채팅룸을 구분하는 값 : pub/sub에서 Topic 역할
    /*
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
    */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}