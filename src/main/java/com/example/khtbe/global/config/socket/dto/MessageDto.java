package com.example.khtbe.global.config.socket.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MessageDto {
    // 메시지  타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType messageType; // 메시지 타입
    private UUID sessionId; // 방 번호
    private UUID senderId; // 채팅을 보낸 사람

    @Builder
    public MessageDto (MessageType messageType, UUID sessionId, UUID senderId){
        this.messageType = messageType;
        this.sessionId = sessionId;
        this.senderId = senderId;
    }
}
