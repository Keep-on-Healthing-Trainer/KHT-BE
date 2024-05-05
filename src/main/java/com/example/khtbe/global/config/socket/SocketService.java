package com.example.khtbe.global.config.socket;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class SocketService {

    private final ObjectMapper objectMapper;
    private Map<String, SocketSession> socketSessions;

    @PostConstruct
    private void init() {
        socketSessions = new LinkedHashMap<>();
    }

    public List<SocketSession> findAllRoom() {
        return new ArrayList<>(socketSessions.values());
    }

    public SocketSession findRoomById(UUID sessionId) {
        return socketSessions.get(sessionId);
    }

    public SocketSession createRoom(String sessionId) {
        SocketSession socketSession = SocketSession.builder()
                .roomId(sessionId)
                .build();
        socketSessions.put(sessionId, socketSession);
        return socketSession;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}