package com.example.khtbe.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
