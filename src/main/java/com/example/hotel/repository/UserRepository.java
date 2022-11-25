package com.example.hotel.repository;

import com.example.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String login);
}