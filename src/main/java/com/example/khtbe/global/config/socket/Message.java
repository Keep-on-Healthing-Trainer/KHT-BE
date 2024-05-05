package com.example.khtbe.global.config.socket;

import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    private MessageType type;
    private String roomId;
    private String sender;

    public enum MessageType {
        ENTER, TALK
    }
}
