package com.example.khtbe.global.config.socket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class SocketSession {
    private String roomId;

    @JsonIgnore
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public SocketSession(String roomId) {
        this.roomId = roomId;
    }

    public void handleActions(WebSocketSession session, Message message, SocketService socketService) {
        if (message.getType().equals(Message.MessageType.ENTER)) {
            sessions.add(session);
        }
        sendMessage(message, socketService);
    }

    public <T> void sendMessage(T message, SocketService socketService) {
        sessions.parallelStream().forEach(session -> socketService.sendMessage(session, message));
    }
}