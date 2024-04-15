package com.example.khtbe.domain.exercise.presentation;

import com.example.khtbe.domain.exercise.presentation.dto.request.ExerciseRequest;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseGraphResponse;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseResponse;
import com.example.khtbe.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @PostMapping("/{id}")
    public ExerciseResponse exercise(@PathVariable UUID id, @RequestBody ExerciseRequest request){
        return exerciseService.exercise(request, id);
    }

    @GetMapping("/user")
    public ExerciseGraphResponse exerciseGraph(Pageable pageable) {
        return exerciseService.exerciseGraph(pageable);
    }
}
