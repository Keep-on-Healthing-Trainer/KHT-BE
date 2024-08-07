package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.exercise.domain.repository.ExerciseRepository;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.request.UpdateRequest;
import com.example.khtbe.domain.user.presentation.dto.response.UserDetailResponse;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserUtil userUtil;
    private final S3Util s3Util;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public UserDetailResponse getUser() {
        User user = userUtil.getUser();

        return UserDetailResponse.builder()
                .name(user.getName())
                .userId(user.getUserId())
                .profileImgeUrl(s3Util.getProfileImageUrl(user.getPath()))
                .totalCounts(user.getTotalCounts())
                .squatCounts(user.getSquatCounts())
                .pushUpCounts(user.getPushUpCounts())
                .sitUpCounts(user.getSitUpCounts())
                .phoneNumber(user.getPhoneNumber())
                .id(user.getId())
                .build();
    }

    @Transactional
    public void updateUser(UpdateRequest request) {
        User user = userUtil.getUser();

        if(request.getUserId() != null) user.setUserId(request.getUserId());
        if(request.getName() != null) user.setName(request.getName());
        if(request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);
    }
}
