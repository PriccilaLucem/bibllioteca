package com.example.biblioteca.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.biblioteca.dto.BookDto;
import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.presenter.BookPresenter;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookPresenter bookPresenter;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    void testGetOneBookController() throws Exception {
        BookModel book = new BookModel();
        book.setId(1L);
        book.setDescription("A book");
        book.setQuantity(10);

        when(bookPresenter.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/book/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    void testPostBookController() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setCategoryId(1L);
        bookDto.setDescription("A new book");
        bookDto.setQuantity(10);

        CategoryModel category = new CategoryModel();
        category.setId(1L);
        category.setName("Fiction");

        BookModel book = new BookModel();
        book.setId(1L);
        book.setDescription("A new book");
        book.setQuantity(10);
        book.setCategory(category);

        when(bookPresenter.postBookPresenter(any(BookDto.class))).thenReturn(book);

        mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDto)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1)) 
                    .andExpect(jsonPath("$.description").value("A new book")) 
                    .andExpect(jsonPath("$.quantity").value(10)); 
    }

    @Test
    void testGetAllBooksController() throws Exception {
        BookModel book = new BookModel();
        book.setId(1L);
        book.setDescription("A book");

        when(bookPresenter.getAllBooksPresenter(anyString())).thenReturn(List.of(book));

        
        mockMvc.perform(get("/api/book")
        .param("search", ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].description").value("A book"));
    }


    @Test
    void testPutBookController() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setCategoryId(1L);
        bookDto.setDescription("An updated book");
        bookDto.setQuantity(5);

        CategoryModel category = new CategoryModel();
        category.setId(1L);
        category.setName("Fiction");

        BookModel updatedBook = new BookModel();
        updatedBook.setId(1L);
        updatedBook.setDescription("An updated book");
        updatedBook.setQuantity(5);
        updatedBook.setCategory(category);

        when(bookPresenter.putBookPresenter(anyLong(), any(BookDto.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/api/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDto)))
            .andExpect(status().isAccepted())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(updatedBook)));
    }
}
