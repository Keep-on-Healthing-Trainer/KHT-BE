package com.example.khtbe.domain.user.domain.repository;

import com.example.khtbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
