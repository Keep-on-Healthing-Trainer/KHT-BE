package com.example.khtbe.domain.user.presentation.dto.response;

import lombok.Data;

@Data
public class UserExerciseCountDTO {
    private String userName;
    private Integer totalCounts;
    private String profileImageUrl;

    public UserExerciseCountDTO(String userName, Integer totalCounts, String profileImageUrl) {
        this.userName = userName;
        this.totalCounts = totalCounts;
        this.profileImageUrl = profileImageUrl;
    }
}
