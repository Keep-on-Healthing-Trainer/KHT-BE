package com.example.khtbe.domain.exercise.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExerciseResponse {
    private final Long id;
    private final Integer count;
    private final String exerciseDate;

    @Builder
    public ExerciseResponse(Long id, Integer count, String exerciseDate){
        this.id = id;
        this.count = count;
        this.exerciseDate = exerciseDate;
    }
}
