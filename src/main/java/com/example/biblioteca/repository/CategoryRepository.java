package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.models.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    
}
