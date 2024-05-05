package com.example.khtbe.domain.exercise.presentation;

import com.example.khtbe.domain.exercise.presentation.dto.request.ExerciseRequest;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseGraphResponse;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseResponse;
import com.example.khtbe.domain.exercise.service.AuthenticationService;
import com.example.khtbe.domain.exercise.service.ExerciseService;
import com.example.khtbe.global.config.socket.SocketService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final AuthenticationService authenticationService;
    private final SocketService socketService;
    private ConcurrentHashMap<String, String> guidLists = new ConcurrentHashMap<>();
    @PostMapping("/{id}")
    public ExerciseResponse exercise(@PathVariable UUID id, @RequestBody ExerciseRequest request){
        return exerciseService.exercise(request, id);
    }

    @GetMapping("/user")
    public ExerciseGraphResponse exerciseGraph(Pageable pageable) {
        return exerciseService.exerciseGraph(pageable);
    }

    @PostMapping("/qr")
    public String getQrCode(HttpServletRequest request, HttpServletResponse response) {
        // HttpSession 객체를 가져옵니다.
        HttpSession session = request.getSession();

        // 고유한 UUID 생성
        String uuid = UUID.randomUUID().toString();

        socketService.createRoom(uuid);

        String qrCodeImageUrl = generateQrCodeImageUrl(uuid);

        if (qrCodeImageUrl != null && !qrCodeImageUrl.isEmpty()) {
            // QR 코드 이미지 URL이 성공적으로 생성되었다면, 이를 반환합니다.
            return qrCodeImageUrl;
        } else {
            // QR 코드 생성에 실패했다면, 에러 페이지로 리다이렉트합니다.
            return "/error";
        }
    }

    private String generateQrCodeImageUrl(String uuid) {
        return "ws://prod-server.xquare.app/kht/exercise/qr?sessionId=" + uuid;
    }
}
