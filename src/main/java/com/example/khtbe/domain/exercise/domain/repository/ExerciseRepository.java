package com.example.khtbe.domain.exercise.domain.repository;

import com.example.khtbe.domain.exercise.domain.Exercise;
import com.example.khtbe.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    Page<Exercise> findExercisesByUser(User user, Pageable pageable);
    Integer countByUser(User user);
}
