package com.example.khtbe.global.config.socket;

import com.example.khtbe.global.config.socket.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;

    // 현재 연결된 세션들
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final Map<UUID,Set<WebSocketSession>> sessionMap = new HashMap<>();

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        log.info("{} 연결됨", session.getId());
        sessions.add(session);
    }

    // 소켓 통신 시 메세지의 전송을 다루는 부분
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        MessageDto messageDto = mapper.readValue(payload, MessageDto.class);
        log.info("session {}", messageDto.toString());

        UUID sessionId = messageDto.getSessionId();

        // 메모리 상에 세션 아이디에 대한 세션 없으면 만들어줌
        if(!sessionMap.containsKey(sessionId)){
            sessionMap.put(sessionId,new HashSet<>());
        }
        Set<WebSocketSession> uuSession = sessionMap.get(sessionId);

        // message 에 담긴 타입을 확인한다.
        // 이때 message 에서 getType 으로 가져온 내용이
        // ChatDTO 의 열거형인 MessageType 안에 있는 ENTER 과 동일한 값이라면
        if (messageDto.getMessageType().equals(MessageDto.MessageType.ENTER)) {
            // sessions 에 넘어온 session 을 담는다.
            uuSession.add(session);
        }
        if (uuSession.size()>=3) {
            removeClosedSession(uuSession);
        }
        sendMessageToChatRoom(messageDto, uuSession);
    }

    // 소켓 종료 확인
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
    }

    private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.removeIf(sess -> !sessions.contains(sess));
    }

    private void sendMessageToChatRoom(MessageDto messageDto, Set<WebSocketSession> uuSession) {
        sessions.parallelStream().forEach(sess -> sendMessage(sess, messageDto));//2
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}