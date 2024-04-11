package com.example.khtbe.domain.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;

public class RefreshToken {
    @Id
    private String userId;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(String refreshToken, String userId) {
        this.refreshToken = refreshToken;
        this.userId = userId;
    }
}
