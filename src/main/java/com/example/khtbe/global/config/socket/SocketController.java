package com.example.khtbe.global.config.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/socket")
public class SocketController {
    private final SocketService socketService;
//    @PostMapping
//    public SocketSession createRoom() {
//        return socketService.createRoom(sessionId);
//    }

    @GetMapping
    public List<SocketSession> findAllRoom() {
        return socketService.findAllRoom();
    }
}
