package com.example.khtbe.domain.exercise.service;

import com.example.khtbe.domain.exercise.domain.Exercise;
import com.example.khtbe.domain.exercise.domain.repository.ExerciseRepository;
import com.example.khtbe.domain.exercise.presentation.dto.request.ExerciseRequest;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseGraphResponse;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseResponse;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.service.exception.UserNotFoundException;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.global.config.security.auth.AuthService;
import com.example.khtbe.global.config.socket.SocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final AuthService authService;
    private final SocketService socketService;

    @Transactional
    public ExerciseResponse exercise(ExerciseRequest request, UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.addCounts(request.getCount());

        userRepository.save(user);

        Exercise exercise = exerciseRepository.save(Exercise.builder()
                .count(request.getCount())
                .user(user)
                .build());

        return new ExerciseResponse(exercise.getId(), exercise.getCount(), exercise.getExerciseDate());
    }

    @Transactional
    public ExerciseGraphResponse exerciseGraph(Pageable pageable) {
        Page<Exercise> exercises = exerciseRepository.findExercisesByUser(userUtil.getUser(), pageable);

        // 운동 횟수의 총합 계산
        int totalCounts = exercises.getContent().stream().mapToInt(Exercise::getCount).sum();

        List<ExerciseGraphResponse.ExerciseResponse> responses = exercises.getContent().stream()
                .map(this::ofExerciseResponse)
                .collect(Collectors.toList());

        return new ExerciseGraphResponse(totalCounts, responses);
    }

    private ExerciseGraphResponse.ExerciseResponse ofExerciseResponse(Exercise exercise) {
        return ExerciseGraphResponse.ExerciseResponse.builder()
                .id(exercise.getId())
                .count(exercise.getCount())
                .exerciseDate(exercise.getExerciseDate())
                .build();
    }
}
