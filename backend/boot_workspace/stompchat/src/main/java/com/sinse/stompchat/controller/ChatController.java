package com.sinse.stompchat.controller;

import com.sinse.stompchat.dto.ChatMessage;
import com.sinse.stompchat.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Controller
public class ChatController {

    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();

    private Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();

    /* favicon 처리
    1) static 디렉토리에 실제로 favicon 이미지를 보유
    2) 컨트롤러에 요청을 처리하되, 이미지를 반환하지 않고 void
     */
    @GetMapping("favicon.ico")
    @ResponseBody
    public void favicon() {}

    /*클라이언트의 접속 요청 처리
     *   클라이언트가 7777/app/connect로 접속하여 이 메서드 실행
     * 
     *  고전적인 방식의 웹소켓에서는 클라이언트가 전송한 프로토콜에 의해 if문을 사용했지만,
     *  STOMP에서는 전통적인 방식이 아닌, 마치 웹요청을 처리하듯 클라이언트의 요청을 구분할 수 있음
     *  따라서 개발자가 별도의 프로토콜을 설계할 필요가 없다
     */
    @MessageMapping("/connect")
    @SendTo("/topic/users") // 이 메서드 실행의 결과는 내부적으로 ObjectMapper에 의한 JSON 문자열이다
    // 또한 이 메서드 실행 결과를 대상 클라이언트를 개발자가 직접 반복문으로 브로드캐스팅을 수행하는 것이 아니라
    // /topic/users 채널을 구독한, 클라이언트한테 자동으로 전송한다
    public Set<String> connect(ChatMessage chatMessage) throws Exception {
        log.debug(chatMessage.getSender() + "의 접속 요청 받음");

        connectedUsers.add(chatMessage.getSender());
        return connectedUsers;
    }

    @MessageMapping("/disconnect")
    @SendTo("/topic/users")
    public Set<String> disconnect(ChatMessage chatMessage) throws Exception {
        connectedUsers.remove(chatMessage.getSender());
        return connectedUsers;
    }

    // 클라이언트의 메시지 전송 처리
    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage chatMessage) throws Exception {
        return chatMessage;
    }

    @MessageMapping("/room.list")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> getRoomList() throws Exception {
        return roomStorage.values();
    }

    // 방만들기 메시지 전송 처리
    @MessageMapping("/room.create")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> createRoom(ChatMessage chatMessage) throws Exception {
        ChatRoom chatRoom = new ChatRoom();
        String roomId = UUID.randomUUID().toString();
        chatRoom.setRoomId(roomId);
        chatRoom.setRoomName(chatMessage.getContent());
        roomStorage.put(roomId, chatRoom);

        return roomStorage.values();
    }

    // 방 참여하기
    @MessageMapping("/room.enter")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> enterRoom(ChatMessage chatMessage) throws Exception {
        // 방을 검색하여, 발견된 방의 Set에 유저명 넣기
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());
        if (chatRoom != null) {
            chatRoom.getUsers().add(chatMessage.getSender());
        }
        return roomStorage.values();
    }

    // 방 나기기
    @MessageMapping("/room.leave")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> leaveRoom(ChatMessage chatMessage) throws Exception {
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());
        if (chatRoom != null) {
            chatRoom.getUsers().remove(chatMessage.getSender());
        }
        return roomStorage.values();
    }


}








