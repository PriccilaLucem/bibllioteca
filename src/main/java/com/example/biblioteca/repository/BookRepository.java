package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.models.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Long>{
}
