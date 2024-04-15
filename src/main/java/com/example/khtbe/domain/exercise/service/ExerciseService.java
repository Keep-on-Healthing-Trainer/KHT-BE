package com.example.khtbe.domain.exercise.service;

import com.example.khtbe.domain.exercise.domain.Exercise;
import com.example.khtbe.domain.exercise.domain.repository.ExerciseRepository;
import com.example.khtbe.domain.exercise.presentation.dto.request.ExerciseRequest;
import com.example.khtbe.domain.exercise.presentation.dto.response.ExerciseResponse;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.service.exception.UserNotFoundException;
import com.example.khtbe.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    @Transactional
    public ExerciseResponse exercise(ExerciseRequest request, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Exercise exercise = exerciseRepository.save(Exercise.builder()
                .count(request.getCount())
                .user(user)
                .build());

        return new ExerciseResponse(exercise.getId(), exercise.getCount(), exercise.getExerciseDate());
    }
}
