package com.example.biblioteca.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.presenter.CategoryPresenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryPresenter categoryPresenter;

    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }


    @Test
    void testGetAllCategoriesController() throws Exception {
        List<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel());
        categories.add(new CategoryModel());

        when(categoryPresenter.getAllCategoriesPresenter()).thenReturn(categories);

        mockMvc.perform(get("/api/category")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }

    @Test
    void testGetCategoryByIdController() throws Exception {
        CategoryModel category = new CategoryModel();
        category.setId(1L);
        category.setName("Category");
        when(categoryPresenter.getOneCategoryPresenter(1L)).thenReturn(category);

        mockMvc.perform(get("/api/category/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) 
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Category"));
    }
}
