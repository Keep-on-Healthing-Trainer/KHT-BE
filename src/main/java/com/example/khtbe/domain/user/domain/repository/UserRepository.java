package com.example.khtbe.domain.user.domain.repository;

import com.example.khtbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    Optional<User> findById(UUID id);
    boolean existsByUserId(String userId);
    List<User> findUserByOrderByTotalCountsDesc();
}
