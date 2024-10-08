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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    @Transactional
    public ExerciseResponse exercise(ExerciseRequest request, UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.addCounts(request.getCount());

        if(request.getExType().equals("SITUP")) {
            user.addSitUpCounts(request.getCount());
        }

        if(request.getExType().equals("PUSHUP")) {
            user.addPushUpCounts(request.getCount());
        }

        if(request.getExType().equals("SQUAT")) {
            user.addSquatCounts(request.getCount());
        }

        userRepository.save(user);

        Exercise exercise = exerciseRepository.save(Exercise.builder()
                .count(request.getCount())
                .user(user)
                .exType(Exercise.ExerciseType.valueOf(request.getExType()))
                .build());

        return new ExerciseResponse(exercise.getId(), exercise.getCount(), exercise.getExerciseDate(), exercise.getExType().getExType());
    }

    @Transactional
    public ExerciseGraphResponse exerciseGraph(Pageable pageable) {
        User user = userUtil.getUser();

        Page<Exercise> sitUpExercises = exerciseRepository.findExercisesByUserAndExType(user, Exercise.ExerciseType.SITUP, pageable);
        Page<Exercise> pushUpExercises = exerciseRepository.findExercisesByUserAndExType(user, Exercise.ExerciseType.PUSHUP, pageable);
        Page<Exercise> squatExercises = exerciseRepository.findExercisesByUserAndExType(user, Exercise.ExerciseType.SQUAT, pageable);

        int totalSitUpCounts = sitUpExercises.getContent().stream().mapToInt(Exercise::getCount).sum();
        int totalPushUpCounts = pushUpExercises.getContent().stream().mapToInt(Exercise::getCount).sum();
        int totalSquatCounts = squatExercises.getContent().stream().mapToInt(Exercise::getCount).sum();

        List<ExerciseGraphResponse.ExerciseResponse> sitUpResponses = sitUpExercises.getContent().stream()
                .map(this::ofExerciseResponse)
                .collect(Collectors.toList());
        List<ExerciseGraphResponse.ExerciseResponse> pushUpResponses = pushUpExercises.getContent().stream()
                .map(this::ofExerciseResponse)
                .collect(Collectors.toList());
        List<ExerciseGraphResponse.ExerciseResponse> squatResponses = squatExercises.getContent().stream()
                .map(this::ofExerciseResponse)
                .collect(Collectors.toList());

        return new ExerciseGraphResponse(totalSitUpCounts, sitUpResponses, totalPushUpCounts, pushUpResponses, totalSquatCounts, squatResponses);
    }

    private ExerciseGraphResponse.ExerciseResponse ofExerciseResponse(Exercise exercise) {
        return ExerciseGraphResponse.ExerciseResponse.builder()
                .id(exercise.getId())
                .count(exercise.getCount())
                .exerciseDate(exercise.getExerciseDate())
                .build();
    }
}
