package com.example.khtbe.domain.user.presentation.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserExerciseCountDTO {
    private String userName;
    private Integer totalCounts;
    private String profileImgUrl;

    public UserExerciseCountDTO(String userName, Integer totalCounts, String profileImgUrl) {
        this.userName = userName;
        this.totalCounts = totalCounts;
        this.profileImgUrl = profileImgUrl;
    }
}
