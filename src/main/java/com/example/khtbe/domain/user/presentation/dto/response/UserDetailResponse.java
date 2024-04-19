package com.example.khtbe.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDetailResponse {
    private final String name;
    private final String userId;
    private final String profileImgeUrl;
    private final Integer totalCounts;
    private final UUID id;

    @Builder
    public UserDetailResponse (String name, String userId, String profileImgeUrl, Integer totalCounts, UUID id) {
        this.name = name;
        this.userId = userId;
        this.profileImgeUrl = profileImgeUrl;
        this.totalCounts = totalCounts;
        this.id = id;
    }
}
