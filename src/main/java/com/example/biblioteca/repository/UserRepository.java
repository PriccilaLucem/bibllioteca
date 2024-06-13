package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String> {
    
}
