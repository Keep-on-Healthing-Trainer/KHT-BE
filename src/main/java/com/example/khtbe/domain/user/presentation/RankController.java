package com.example.khtbe.domain.user.presentation;

import com.example.khtbe.domain.user.presentation.dto.response.UserExerciseCountDTO;
import com.example.khtbe.domain.user.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankController {
    private final RankService rankService;

    @GetMapping
    public ResponseEntity<?> getRankings() {
        List<UserExerciseCountDTO> rankings = rankService.getRankings();
        return ResponseEntity.ok(Collections.singletonMap("RankingResponse", rankings));
    }
}