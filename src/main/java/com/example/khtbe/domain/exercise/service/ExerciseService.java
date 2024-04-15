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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    @Transactional
    public ExerciseResponse exercise(ExerciseRequest request, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Exercise exercise = exerciseRepository.save(Exercise.builder()
                .count(request.getCount())
                .user(user)
                .build());

        return new ExerciseResponse(exercise.getId(), exercise.getCount(), exercise.getExerciseDate());
    }

    public ExerciseGraphResponse exerciseGraph(Pageable pageable) {
        Page<Exercise> exercises = exerciseRepository.findExercisesByUser(userUtil.getUser(), pageable);

        return new ExerciseGraphResponse(exercises.getTotalPages(),
                exercises.stream().map(this::ofExerciseResponse).collect(Collectors.toList()));
    }

    private ExerciseGraphResponse.ExerciseResponse ofExerciseResponse(Exercise exercise) {
        return ExerciseGraphResponse.ExerciseResponse.builder()
                .id(exercise.getId())
                .count(exercise.getCount())
                .exerciseDate(exercise.getExerciseDate())
                .build();
    }
}
