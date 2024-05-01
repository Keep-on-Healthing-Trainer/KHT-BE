package com.example.khtbe.domain.exercise.presentation;

import com.example.khtbe.domain.exercise.presentation.dto.request.ExerciseRequest;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseGraphResponse;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseResponse;
import com.example.khtbe.domain.exercise.service.ExerciseService;
import com.example.khtbe.domain.exercise.service.IpCheck;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

//    @GetMapping(value = "/qr")
//    public String loginQr(HttpServletRequest request, HttpServletResponse response, IpCheck ic) {
//
//        int qrResult = exerciseService.exerciseGetQr(request,response,ic);
//
//        if (qrResult == 1) return "/exercise/MainQrLogin";
//        else return "/error";
//    }
}
