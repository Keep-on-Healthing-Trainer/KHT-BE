package com.example.khtbe.domain.exercise.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExerciseResponse {
    private final Long id;
    private final Integer count;
    private final String exerciseDate;
    private final String exType;

    @Builder
    public ExerciseResponse(Long id, Integer count, String exerciseDate, String exType){
        this.id = id;
        this.count = count;
        this.exerciseDate = exerciseDate;
        this.exType = exType;
    }
}
