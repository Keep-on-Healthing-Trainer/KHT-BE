package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.request.SignupRequest;
import com.example.khtbe.domain.user.service.exception.UserIdAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request){
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new UserIdAlreadyExistException();
        }

        User user = userRepository.save(User.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build());
    }
}