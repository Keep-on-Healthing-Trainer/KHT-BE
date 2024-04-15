package com.example.khtbe.domain.exercise.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExerciseResponse {
    private final Long id;
    private final Integer count;
    private final Double kcal;
    private final String exerciseDate;

    @Builder
    public ExerciseResponse(Long id, Integer count, Double kcal, String exerciseDate){
        this.id = id;
        this.count = count;
        this.kcal = count*0.9;
        this.exerciseDate = exerciseDate;
    }
}
