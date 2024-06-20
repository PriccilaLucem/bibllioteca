package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.biblioteca.models.BookModel;
import java.util.List;

public interface BookRepository extends JpaRepository<BookModel, Long>{
    @Query("SELECT b FROM BookModel b " +
           "WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(b.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(b.category.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
           List<BookModel> findByTitleOrDescriptionOrCategoryName(@Param("searchTerm") String searchTerm);

}
