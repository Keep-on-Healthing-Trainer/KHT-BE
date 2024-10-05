package com.example.khtbe.global.config.socket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
