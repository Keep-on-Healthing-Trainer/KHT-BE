package com.example.khtbe.domain.exercise.domain;

import com.example.khtbe.domain.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @ManyToOne
    @JoinColumn(name = "uuid")
    private User user;

    @Builder
    public Exercise(Integer count, String exerciseDate, Double kcal, User user){
        this.count = count;
        this.exerciseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM.dd"));
        this.kcal = count*0.9;
        this.user = user;
    }
}