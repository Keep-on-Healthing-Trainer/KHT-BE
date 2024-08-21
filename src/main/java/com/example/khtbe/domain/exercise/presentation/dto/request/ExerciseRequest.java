package com.example.khtbe.domain.exercise.presentation.dto.request;

import com.example.khtbe.domain.exercise.domain.Exercise;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ExerciseRequest {
    @Max(500)
    @NotNull
    private Integer count;

    @NotBlank
    private String exType;
}
