package com.example.khtbe.domain.user.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UpdateRequest {
    @NotNull
    @Size(min = 6, max = 15)
    private String userId;

    @NotNull
    @Size(max = 8)
    private String name;

    @NotNull
    @Size(max = 11)
    private String phoneNumber;
}
