package com.sinse.broadcastapp.model.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.broadcastapp.dto.ResponseChat;
import com.sinse.broadcastapp.dto.ResponseConnect;
import com.sinse.broadcastapp.dto.User;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/*
* 웹소켓을 구현하는 방법
* 1) 순수 javaEE의 ApI를 이용하는 방법
* 2) Spring이 지원하는 API 이용하는 방법

메시지 교환방법
1) 개발자가 직접 프로토콜을 설계하는 방법
2) STOMP 프로토콜을 이용하는 방법
    -WebSocket 위에서 메시지를 주고받기 위한 메시지 프로토콜(개발자가 직접 설계할 필요없다. 편하다)
*/
@Slf4j
@ServerEndpoint("/ws/multi")
@Component
public class ChatEndpoint {

    // 접속자 명단
    // 중복 가능하지 않은 Set으로 접속자 명단을 만들자
    // ServerEndpoint는 요청마다 하나씩 인스턴스를 생성하므로, static 선언하여 공용으로 사용하자
    private static Set<String> userIdList = new HashSet(); // 이거는 클라이언트에게 접속자 정보를 보내기 위한 리스트

    private static Set<Session> userList = new HashSet();

    // jav <-> json 과의 변환을 담당해주는 객체
    private static ObjectMapper mapper = new ObjectMapper();
    private final ObjectMapper objectMapper;

    public ChatEndpoint() {
        this.objectMapper = new ObjectMapper(); // fallback
    }

    public ChatEndpoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // 연결 감지
    @OnOpen
    public void onOpen(Session session) throws Exception {
//        User user = new User();
//        user.setId(session.getId());
//        user.setName("sue");

        // 접속과 동시에 클라이언트에게 접속자 정보를 구성해서 보내자 (프로토콜 형식에 맞게)
        /*
         {
            "responseType": "connect",
            "data" : [
                {
                    "1", "2", "3",..
                }
            ]
          }
         */
        userIdList.add(session.getId());
        userList.add(session);

        ResponseConnect responseConnect = new ResponseConnect();
        responseConnect.setResponseType("connect");
        responseConnect.setData(userIdList);

        // 접속과 동시에, 클라이언트에게 서버의 접속자 명단을 전송하자!!
        String json = mapper.writeValueAsString(responseConnect);

        session.getAsyncRemote().sendText(json);
    }

    // 메시지 감지
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.debug("클라이언트의 메시지는 " + message);
        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();

        if(requestType.equals("chat")){
            log.debug("클라이언트가 채팅을 원하는 군요");

        /*
                    {
                        "requestType" : "chat",
                        "sender" : "sessionid",
                        "data" : "나 배고파"
                    }
        */

           ResponseChat responseChat = new ResponseChat();
           responseChat.setResponseType("chat");
           responseChat.setSender(session.getId());
           String data = jsonNode.get("data").asText();
           responseChat.setData(data);

           String json = objectMapper.writeValueAsString(responseChat);
           log.debug("json 메시지는 " + json);

            for( Session ss : userList ){
                log.debug("서버의 메시지는 :" + json);
                if(ss.isOpen()){
                    ss.getAsyncRemote().sendText(json);
                }
            }
        }
        // 클라이언트에게 메시지 전송
        // 멀티 캐스팅이 되려면, 브로드캐스팅 시켜야 한다
    }
    @OnClose
    public void onClose(Session session) throws Exception {
        // Session이 끊기면 Set에서 제거
        userList.remove(session.getId());
        userIdList.remove(session.getId());
    }
    @OnError
    public void onError(Session session, Throwable error) throws Exception {
        userList.remove(session.getId());
        userIdList.remove(session.getId());
    }
}
