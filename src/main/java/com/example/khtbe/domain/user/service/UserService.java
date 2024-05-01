package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.exercise.domain.repository.ExerciseRepository;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.response.UserDetailResponse;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserUtil userUtil;
    private final S3Util s3Util;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public UserDetailResponse getUser() {
        User user = userUtil.getUser();

        return UserDetailResponse.builder()
                .name(user.getName())
                .userId(user.getUserId())
                .profileImgeUrl(s3Util.getProfileImageUrl(user.getPath()))
                .totalCounts(exerciseRepository.countByUser(user))
                .phoneNumber(user.getPhoneNumber())
                .id(user.getId())
                .build();
    }
}
