package com.example.khtbe.domain.exercise.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ExerciseGraphResponse {
    private final int totalSitUpCounts;
    private final List<ExerciseResponse> sitUpResponses;
    private final int totalPushUpCounts;
    private final List<ExerciseResponse> pushUpResponses;
    private final int totalSquatCounts;
    private final List<ExerciseResponse> squatResponses;

    @Builder
    @Getter
    public static class ExerciseResponse {
        private final Long id;
        private final String exerciseDate;
        private final Integer count;
    }
}
