package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.service.CategoryService;

import java.util.List;

@Component
public class CategoryPresenter {
    
    @Autowired
    private CategoryService categoryService;

    public CategoryModel getOneCategoryPresenter(Long id){
        return categoryService.findCategoryByIdService(id);
    }

    public List<CategoryModel> getAllCategoriesPresenter(){
        return categoryService.findAllCategoriesService();
    }
}
