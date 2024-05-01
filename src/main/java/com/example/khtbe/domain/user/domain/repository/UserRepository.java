package com.example.khtbe.domain.user.domain.repository;

import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.presentation.dto.response.UserExerciseCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    Optional<User> findById(UUID id);
    boolean existsByUserId(String userId);
    @Query("SELECT new com.example.khtbe.domain.user.presentation.dto.response.UserExerciseCountDTO(u.name, COUNT(e) AS totalCounts, u.path AS profileImageUrl) FROM User u JOIN u.exercises e GROUP BY u.id, u.name, u.path ORDER BY totalCounts DESC")
    List<UserExerciseCountDTO> findUserExerciseCounts();
}
