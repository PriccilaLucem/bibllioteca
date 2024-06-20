package com.example.biblioteca.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.service.CategoryService;

class CategoryPresenterTest {

    @InjectMocks
    private CategoryPresenter categoryPresenter;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOneCategoryPresenter() {
        CategoryModel category = new CategoryModel();
        category.setId(1L);
        category.setName("Fiction");

        when(categoryService.findCategoryByIdService(1L)).thenReturn(category);

        CategoryModel foundCategory = categoryPresenter.getOneCategoryPresenter(1L);

        assertEquals(category, foundCategory);
    }

    @Test
    void testGetAllCategoriesPresenter() {
        CategoryModel category1 = new CategoryModel();
        category1.setId(1L);
        category1.setName("Fiction");

        CategoryModel category2 = new CategoryModel();
        category2.setId(2L);
        category2.setName("Non-Fiction");

        List<CategoryModel> categories = List.of(category1, category2);

        when(categoryService.findAllCategoriesService()).thenReturn(categories);

        List<CategoryModel> foundCategories = categoryPresenter.getAllCategoriesPresenter();

        assertEquals(categories, foundCategories);
    }
}
