package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.exercise.domain.repository.ExerciseRepository;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.response.UserExerciseCountDTO;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankService {
    private final UserRepository userRepository;
    private final S3Util s3Util;

    public RankService(UserRepository userRepository, S3Util s3Util) {
        this.userRepository = userRepository;
        this.s3Util = s3Util;
    }
    public List<UserExerciseCountDTO> getRankings() {
        List<User> topUsers = userRepository.findUserByOrderByTotalCountsDesc();

        return topUsers.stream().map(user -> new UserExerciseCountDTO(
                user.getName(),
                user.getTotalCounts(),
                s3Util.getProfileImageUrl(user.getPath())
        )).collect(Collectors.toList());
    }
}