package com.sinse.chatroomapp.model.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.dto.*;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@ServerEndpoint(value="/chat/multi", configurator = HttpSessionConfigurator.class)
@Component
public class ChatEndPoint {

    // 서버 측에서 필요한 접속자 명단
    private static Set<Session> userList = new HashSet<>();
    // 클라이언트에게 전달할 접속자 명단
    private static Set<Member> memberList = new HashSet<>();
    // 클라이언트에게 전달할 룸 정보
    private static Set<Room> roomList = new HashSet<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws Exception {
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        if(httpSession != null){
            Member member = (Member) httpSession.getAttribute("member");

            // 어차피 클라이언트 브라우저에서는 접속자 명단만 필요하므로, 서버측에서 너무나 예민한 정보는
            // 클라이언트에게 보내줄 필요가 없다. 따라서 Member 모델에서 id 만 추출해보자
            session.getUserProperties().put("member", member);
            userList.add(session);

            // 접속한 클라이언트가 알아야할 정보 전송 (누가 접속, 방들의 정보)
            // 단, 클라이언트와의 통신은 정해진 프로토콜을 지켜 보내자
            /*
                  {
                        "responseType" : "createRoom",
                        "memberList" : [
                            {
                                id : "mario",
                                name : "마리오",
                                email : "mario@naver.com"
                            }
                        ],
                        "roomList" : [

                        ]
                  }
             */
            // 응답 정보 만들기
            CreateRoomResponse createRoomResponse = new CreateRoomResponse();
            createRoomResponse.setResponseType("createRoom");

            // 회원 정보 채우기
            Member obj = new Member();
            obj.setId(member.getId());
            obj.setName(member.getName());
            obj.setEmail(member.getEmail());
            memberList.add(obj);

            createRoomResponse.setMemberList(memberList);

            String json = objectMapper.writeValueAsString(createRoomResponse); // java --> json 문자열로 변환

            session.getAsyncRemote().sendText(json);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.debug(message);

        // 파싱 시작
        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();
        if(requestType.equals("createRoom")){
            log.debug("방 만들어줄게");
            String userId =  jsonNode.get("userId").asText();
            String roomName = jsonNode.get("roomName").asText();

            // 로그인 시 사용된 HttpSession에 들어있는 회원정보와, 웹소켓을 통해 전달된 회원 정보를 비교하여 일치하는 지 검증
            Member member = (Member) session.getUserProperties().get("member");
            if(!member.getId().equals(userId)){
                // 클라이언트에게 에러를 전송!

            } else {
                // 방 고유 식별자
                UUID uuid = UUID.randomUUID();
                Room room = new Room();
                room.setUUID(uuid.toString());
                room.setMaster(userId);
                room.setRoomName(roomName);

                // 참여자 목록
                Set users = new HashSet();
                Member obj = new Member();
                obj.setId(member.getId());
                obj.setName(member.getName());
                obj.setEmail(member.getEmail());

                users.add(obj);
                room.setUserList(users);

                roomList.add(room);

                /*
                    클라이언트에게 전송할 응답 프로토콜
                    {
                        "responseType" : "createRoom",
                        "memberList" : [
                            {

                            }
                        ],
                        "roomList" : [
                            {
                                "uuid" : "E12432",
                                "master" : "mario",
                            }
                        ]
                    }
                 */
                CreateRoomResponse createRoomResponse = new CreateRoomResponse();
                createRoomResponse.setResponseType("createRoom");
                createRoomResponse.setMemberList(memberList);
                createRoomResponse.setRoomList(roomList);
                String json = objectMapper.writeValueAsString(createRoomResponse);

                // 방이 생성된 사실을 서버에 접속한 모든~~ 클라이언트가 알아야 하므로,
                // 브로드캐스팅의 대상이 된다
                for(Session ss : userList){
                    ss.getAsyncRemote().sendText(json);
                }
            }

        } else if(requestType.equals("enterRoom")){
            log.debug("방 들어가");
            /*
                1) 요청한 클라이언트를 선택한 방에 밀어넣기
                    - 넘겨받은 uuid를 이용하여 방선택
                    - 해당 Roomid 보유한 Set에 클라이언트를 참여자 등록(중복 피하기)
             */
            String uuid = jsonNode.get("uuid").asText();
            Room room = null;

            for(Room r : roomList){
                if(r.getUUID().equals(uuid)){
                    room = r;
                    break;
                }
            }
            /*
            선언적 프로그래밍 방식으로도 위의 작업을 진행할 수 있다
            데이터 집합을 다룰 때 최적화되어있음
            */
            /*
            roomList.stream()
                    .filter(r -> uuid.equals(r.getUUID())) // 조건에 맞는 요소만 추림
                    .findFirst() // 조건에 맞는 첫번째 요소 반환
                    .orElse(null); // 없으면 null 리턴
             */
            // 찾아낸 Room 안에 채팅 참여자로 등록(등록되어 있지 않은 사람만 등록)

            // 현재 클라이언트와 연결된 session에 담겨진 회원 정보를 추출
            Member member = (Member) session.getUserProperties().get("member");

            // 룸에 들어있는 유저들 정보와 비교하여 같지 않은 경우에만 유저를 방에 추가
            boolean exists = false;
            for(Member obj : room.getUserList()){
                if(obj.getId().equals(member.getId())){
                    exists = true; // 중복 발견
                    break;
                }
            }
            // 룸에 등록되어 있지 않다면..
            Member obj;
            if(exists == false){
                obj = new Member();
                obj.setId(member.getId());
                obj.setName(member.getName());
                obj.setEmail(member.getEmail());
                room.getUserList().add(obj); // 채팅방 참여자로 등록
            }

            /*
            {
                responseType : "enterRoom",
                room : {
                }
            }
             */
            EnterRoomResponse enterRoomResponse = new EnterRoomResponse();
            enterRoomResponse.setResponseType("enterRoom");
            enterRoomResponse.setRoom(room);

            String json = objectMapper.writeValueAsString(enterRoomResponse);

            session.getAsyncRemote().sendText(json);
        } else if (requestType.equals("chat")){
            log.debug("채팅 요청 받음");

            String sender = jsonNode.get("sender").asText();
            String data = jsonNode.get("data").asText();
            String uuid =  jsonNode.get("uuid").asText();

            /*
            1) 같은 방에 있는 유저들에게 브로드캐스팅한다
             */
            Room room = null;
            for(Room r : roomList){
                if(r.getUUID().equals(uuid)){
                    room = r;
                    break;
                }
            }

            /*
            {
                responseType : "chat",
                sender : ,
                data : ,
                uuid :
            }
             */

            // 전송 메시지 구성하기
            ChatResponse chatResponse = new ChatResponse();
            chatResponse.setResponseType("chat");
            chatResponse.setSender(sender);
            chatResponse.setData(data);
            String json = objectMapper.writeValueAsString(chatResponse);

            for(Session ss : userList){
                for(Member member : room.getUserList()){
                    Member obj = (Member) ss.getUserProperties().get("member");
                    if(obj.getId().equals(member.getId())){
                        ss.getAsyncRemote().sendText(json);
                    }
                }
            }

        } else if(requestType.equals("exitRoom")){

        }
    }
    @OnClose
    public void onClose(Session session) throws Exception {
//        Member member = (Member) session.getUserProperties().get("member");
//
//        memberList.remove(member);
//        userList.remove(session);
//
//        Room room = null;
//        Member mr = null;
//        for( Room r : roomList){
//            for(Member obj : r.getUserList()){
//                if(obj.getId().equals(member.getId())){
//                    room = r;
//                    mr = obj;
//                    break;
//                }
//            }
//        }
//        room.getUserList().remove(mr);
//
//        CloseResponse closeResponse = new CloseResponse();
//        closeResponse.setResponseType("close");
//        closeResponse.setMemberList(memberList);
//        closeResponse.setRoomList(roomList);
//
//        session.getAsyncRemote().sendText(objectMapper.writeValueAsString(closeResponse));
    }
}


