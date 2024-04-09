package com.example.khtbe.domain.exercise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer count;

    @NotNull
    private String exerciseDate;

    @NotNull
    private Double kcal;

    @Builder
    public Exercise(Integer count, String exerciseDate, Double kcal){
        this.count = count;
        this.exerciseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM.dd"));
        this.kcal = count*0.9;
    }
}
