package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.models.UserModel;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
}
