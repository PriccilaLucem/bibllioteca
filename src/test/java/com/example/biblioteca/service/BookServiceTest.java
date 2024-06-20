package com.example.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.repository.BookRepository;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBookService() {
        BookModel book = new BookModel();
        CategoryModel category = new CategoryModel();
        book.setCategory(category);
        book.setDescription("A fiction book");
        book.setQuantity(10);

        when(bookRepository.save(book)).thenReturn(book);

        BookModel createdBook = bookService.createBookService(book);

        assertEquals(book, createdBook);
        verify(bookRepository).save(book);
    }

    @Test
    void testFindBookById() {
        BookModel book = new BookModel();
        book.setId(1L);
        CategoryModel category = new CategoryModel();
        book.setCategory(category);
        book.setDescription("A fiction book");
        book.setQuantity(10);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookModel foundBook = bookService.findBookById(1L);

        assertEquals(book, foundBook);
    }

    @Test
    void testFindBookByIdNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            bookService.findBookById(1L);
        });
    }


    @Test
    public void testListAllBooks() {
        // Arrange: Simular o comportamento do repository
        CategoryModel category1 = new CategoryModel();
        category1.setId(1L);
        category1.setName("Programming");

        BookModel book1 = new BookModel();
        book1.setId(1L);
        book1.setName("Java Programming");
        book1.setDescription("Learn Java programming basics");
        book1.setCategory(category1);

        BookModel book2 = new BookModel();
        book2.setId(2L);
        book2.setName("Spring Boot in Action");
        book2.setDescription("Learn Spring Boot framework");
        book2.setCategory(category1);

        List<BookModel> mockBooks = new ArrayList<>();
        mockBooks.add(book1);
        mockBooks.add(book2);

        when(bookRepository.findByTitleOrDescriptionOrCategoryName(anyString())).thenReturn(mockBooks);

        List<BookModel> foundBooks = bookService.listAllBooks("Java");


        assertEquals(2, foundBooks.size());
        assertEquals("Java Programming", foundBooks.get(0).getName());
        assertEquals("Spring Boot in Action", foundBooks.get(1).getName());
    }
   
    @Test
    void testUpdateBook() {
        BookModel existingBook = new BookModel();
        CategoryModel category = new CategoryModel();
        
        existingBook.setId(1L);
        existingBook.setCategory(category);
        existingBook.setDescription("A fiction book");
        existingBook.setQuantity(10);

        BookModel updatedBook = new BookModel();
        updatedBook.setCategory(category);
        updatedBook.setDescription("A non-fiction book");
        updatedBook.setQuantity(5);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        BookModel result = bookService.updateBook(1L, updatedBook);

        assertEquals("A non-fiction book", result.getDescription());
        assertEquals(5, result.getQuantity());
    }

    @Test
    void testDeleteBookRepository() {
        BookModel book = new BookModel();
        book.setId(1L);
        CategoryModel category = new CategoryModel();
        book.setCategory(category);
        book.setDescription("A fiction book");
        book.setQuantity(10);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBookRepository(1L);

        verify(bookRepository).delete(book);
    }
}
