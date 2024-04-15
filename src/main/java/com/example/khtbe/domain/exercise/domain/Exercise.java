package com.example.khtbe.domain.exercise.domain;

import com.example.khtbe.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Exercise(Integer count, String exerciseDate, User user){
        this.count = count;
        this.exerciseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM.dd"));
        this.user = user;
    }
}