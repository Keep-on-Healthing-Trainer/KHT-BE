package com.example.khtbe.domain.exercise.domain;

import com.example.khtbe.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @ManyToOne
    @JoinColumn(name = "uuid")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExerciseType exType;

    @Builder
    public Exercise(Integer count, String exerciseDate, User user, ExerciseType exType){
        this.count = count;
        this.exerciseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM.dd"));
        this.user = user;
        this.exType = exType;
    }

    @Getter
    @RequiredArgsConstructor
    public enum ExerciseType {
        SITUP("윗몸일으키기"),
        PUSHUP("팔굽혀펴기"),
        SQUAT("스쿼트");

        private final String exType;
    }
}