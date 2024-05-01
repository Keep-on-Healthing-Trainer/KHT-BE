package com.example.khtbe.domain.user.presentation;

import com.example.khtbe.domain.exercise.domain.repository.ExerciseRepository;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.response.UserExerciseCountDTO;
import com.example.khtbe.domain.user.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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