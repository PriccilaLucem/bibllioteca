package com.example.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCategoryByIdService() {
        CategoryModel category = new CategoryModel();
        category.setId(1L);
        category.setName("Fiction");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryModel foundCategory = categoryService.findCategoryByIdService(1L);

        assertEquals(category, foundCategory);
    }

    @Test
    void testFindCategoryByIdServiceNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            categoryService.findCategoryByIdService(1L);
        });
    }

    @Test
    void testFindAllCategoriesService() {
        CategoryModel category1 = new CategoryModel();
        category1.setId(1L);
        category1.setName("Fiction");

        CategoryModel category2 = new CategoryModel();
        category2.setId(2L);
        category2.setName("Non-Fiction");

        List<CategoryModel> categories = List.of(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryModel> foundCategories = categoryService.findAllCategoriesService();

        assertEquals(categories, foundCategories);
    }
}
