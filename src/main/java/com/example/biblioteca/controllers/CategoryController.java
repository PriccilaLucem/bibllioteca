package com.example.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.presenter.CategoryPresenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryPresenter categoryPresenter;
    
    @GetMapping()
    public ResponseEntity<List<CategoryModel>> getAllCategoriesController() {
        return ResponseEntity.ok().body(categoryPresenter.getAllCategoriesPresenter());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryByIdController(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryPresenter.getOneCategoryPresenter(id));
    }
    
    
}
