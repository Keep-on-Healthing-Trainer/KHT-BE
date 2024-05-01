package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.exercise.domain.repository.ExerciseRepository;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.response.UserExerciseCountDTO;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {
    private final UserRepository userRepository;

    public RankService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserExerciseCountDTO> getRankings() {
        return userRepository.findUserExerciseCounts();
    }
}
