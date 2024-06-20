package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryModel  findCategoryByIdService(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category does not exists"));
    }
    
    public List<CategoryModel> findAllCategoriesService(){
        return categoryRepository.findAll();
    }
    
}
