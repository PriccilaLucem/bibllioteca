package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.models.AdmUserModel;
import java.util.Optional;


public interface AdmUserRepository extends JpaRepository<AdmUserModel, String>    {
    
    Optional<AdmUserModel> findByEmail(String email);
}
