package com.example.khtbe.domain.user.service;

import com.example.khtbe.domain.user.presentation.dto.response.TokenResponse;
import com.example.khtbe.domain.user.service.exception.UserNotFoundException;
import com.example.khtbe.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public TokenResponse refresh(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw UserNotFoundException.EXCEPTION;
        }
        String accountId = jwtTokenProvider.getSubject(refreshToken);
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(accountId);
        } catch (UsernameNotFoundException e) {
            throw UserNotFoundException.EXCEPTION;
        }
        String newAccessToken = jwtTokenProvider.generateAccessToken(accountId);
        TokenResponse response = TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();

        return response;
    }
}
