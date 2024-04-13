package com.example.khtbe.domain.user.domain;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Id;

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
