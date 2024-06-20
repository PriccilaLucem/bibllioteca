package com.example.biblioteca.repository;

import com.example.biblioteca.models.BookModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;


public class BookRepositoryTest {

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByTitleOrDescription() {
        BookModel book1 = new BookModel();
        book1.setName("Java Programming");
        book1.setDescription("Learn Java programming basics");
        bookRepository.save(book1);

        BookModel book2 = new BookModel();
        book2.setName("Spring Boot in Action");
        book2.setDescription("Learn Spring Boot framework");
        bookRepository.save(book2);

        BookModel book3 = new BookModel();
        book3.setName("Clean Code");
        book3.setDescription("Writing clean code practices");
        bookRepository.save(book3);


        when(bookRepository.findByTitleOrDescriptionOrCategoryName(anyString())).thenReturn(List.of(book1,book2));

        List<BookModel> foundBooks = bookRepository.findByTitleOrDescriptionOrCategoryName("");
        assertEquals(2, foundBooks.size()); 
        assertEquals("Java Programming", foundBooks.get(0).getName());
        assertEquals("Spring Boot in Action", foundBooks.get(1).getName());
    }
}
