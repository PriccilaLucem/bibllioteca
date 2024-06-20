package com.example.biblioteca.service;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LendBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LendBookService lendBookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLendBookService() {
        BookModel book = new BookModel();
        book.setId(1L);
        book.setName("Test Book");
        book.setDescription("A test book description");
        book.setQuantity(5);

        UserModel user = new UserModel();
        user.setId("string");

        when(bookRepository.save(any(BookModel.class))).thenReturn(book);
        when(userRepository.save(any(UserModel.class))).thenReturn(user);

        UserModel updatedUser = lendBookService.lendBookService(user, book);

        assertEquals(4, book.getQuantity());
        assertEquals(book, updatedUser.getBook());
        verify(bookRepository, times(1)).save(book);
        verify(userRepository, times(1)).save(user);
    }
}
