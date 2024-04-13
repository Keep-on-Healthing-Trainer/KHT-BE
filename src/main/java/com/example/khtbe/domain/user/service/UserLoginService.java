package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.user.domain.RefreshToken;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.domain.repository.RefreshTokenRepository;
import com.example.khtbe.domain.user.domain.repository.UserRepository;
import com.example.khtbe.domain.user.presentation.dto.request.LoginRequest;
import com.example.khtbe.domain.user.presentation.dto.response.TokenResponse;
import com.example.khtbe.domain.user.service.exception.PasswordMismatchException;
import com.example.khtbe.domain.user.service.exception.UserNotFoundException;
import com.example.khtbe.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getUserId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .userId(user.getUserId())
                        .refreshToken(jwtTokenProvider.generateRefreshToken(user.getUserId()))
                        .build()).getRefreshToken())
                .build();
    }
}