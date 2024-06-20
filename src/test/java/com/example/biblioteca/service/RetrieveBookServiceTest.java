
package com.example.biblioteca.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.repository.UserRepository;

public class RetrieveBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RetrieveBookService retrieveBookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveBookService() {
        BookModel book = new BookModel();
        book.setId(1L);
        book.setQuantity(5);

        UserModel user = new UserModel();
        user.setId("string");
        user.setBook(book);

        when(bookRepository.save(any(BookModel.class))).thenReturn(book);
        when(userRepository.save(any(UserModel.class))).thenReturn(user);

        UserModel updatedUser = retrieveBookService.retrieveBookService(user, book);

        assertNull(updatedUser.getBook());
        assertEquals(6, book.getQuantity());

        verify(bookRepository, times(1)).save(book);
        verify(userRepository, times(1)).save(user);
    }
}
