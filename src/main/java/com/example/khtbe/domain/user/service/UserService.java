package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.request.UpdateRequest;
import com.example.khtbe.domain.user.presentation.dto.response.UserDetailResponse;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserUtil userUtil;
    private final S3Util s3Util;
    private final UserRepository userRepository;

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

    @Transactional
    public List<UserDetailResponse> userList(String name) {
        List<User> users = userRepository.findByNameContaining(name);

        return users.stream()
                .map(user -> UserDetailResponse.builder()
                        .name(user.getName())
                        .userId(user.getUserId())
                        .profileImgeUrl(s3Util.getProfileImageUrl(user.getPath()))
                        .totalCounts(user.getTotalCounts())
                        .squatCounts(user.getSquatCounts())
                        .pushUpCounts(user.getPushUpCounts())
                        .sitUpCounts(user.getSitUpCounts())
                        .phoneNumber(user.getPhoneNumber())
                        .id(user.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
