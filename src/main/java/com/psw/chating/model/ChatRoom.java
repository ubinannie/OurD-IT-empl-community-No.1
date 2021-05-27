package com.psw.chating.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//롬복을 사용해서 getter / setter를 자동으로 만들어주는 어노테이션 생성
@Getter
@Setter
@ToString
// Reids에 저장되는 객체 -> Serializq가 가능해야함으로 Serializable 선언
public class ChatRoom implements Serializable{
	
	private static final long serialVersionUID = 6494678977089006639L;
	
	private String roomId;
	private String name;
	// WebSockConfig에서 pub/sub방식을 이용 : 구독자 관리가 알아서 되므로 웹소켓 세션 관리가 필요 없어짐
	// 또한 발송의 구현도 알아서 해결 -> 일일이 클라이언트에서 메시지를 발송하는 구현이 필요 없어짐 
	// private Set<WebSocketSession> sessions = new HashSet<>();

	 public static ChatRoom create(String name) {
	        ChatRoom chatRoom = new ChatRoom();
	        chatRoom.roomId = UUID.randomUUID().toString();
	        chatRoom.name = name;
	        return chatRoom;
	    }
	 
	 /*
	 public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
	        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
	            sessions.add(session);
	            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
	        }
	        sendMessage(chatMessage, chatService);
	    }

	    public <T> void sendMessage(T message, ChatService chatService) {
	        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
	    }
	*/
	 
}
