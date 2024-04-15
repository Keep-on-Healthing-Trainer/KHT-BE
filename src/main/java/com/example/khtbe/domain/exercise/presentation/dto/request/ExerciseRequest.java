package com.example.khtbe.domain.exercise.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
public class ExerciseRequest {
    @Max(500)
    @NotNull
    Integer Count;
}
