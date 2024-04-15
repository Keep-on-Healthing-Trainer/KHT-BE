package com.example.khtbe.domain.exercise.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ExerciseGraphResponse {
    private final int totalCounts;
    private final List<ExerciseResponse> exerciseResponses;

    @Builder
    @Getter
    public static class ExerciseResponse {
        private final Long id;
        private final String exerciseDate;
        private final Integer count;
    }
}
