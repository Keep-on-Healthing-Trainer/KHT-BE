package com.example.khtbe.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDetailResponse {
    private final String name;
    private final String userId;
    private final String profileImgeUrl;
    private final String phoneNumber;
    private final Integer totalCounts;
    private final Integer sitUpCounts;
    private final Integer pushUpCounts;
    private final Integer squatCounts;
    private final String path;
    private final UUID id;

    @Builder
    public UserDetailResponse (String name, String userId, String profileImgeUrl, String phoneNumber,Integer totalCounts, Integer sitUpCounts, Integer pushUpCounts, Integer squatCounts, UUID id, String path) {
        this.name = name;
        this.userId = userId;
        this.path = path;
        this.profileImgeUrl = profileImgeUrl;
        this.phoneNumber = phoneNumber;
        this.totalCounts = totalCounts;
        this.sitUpCounts = sitUpCounts;
        this.pushUpCounts = pushUpCounts;
        this.squatCounts = squatCounts;
        this.id = id;
    }
}
