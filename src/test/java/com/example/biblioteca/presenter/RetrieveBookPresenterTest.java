package com.example.biblioteca.presenter;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.service.BookService;
import com.example.biblioteca.service.RetrieveBookService;
import com.example.biblioteca.service.UserService;

public class RetrieveBookPresenterTest {

    @Mock
    private RetrieveBookService retrieveBookService;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private RetrieveBookPresenter retrieveBookPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveBookPresenter() {
        BookModel book = new BookModel();
        book.setId(1L);
        book.setQuantity(5);

        UserModel user = new UserModel();
        user.setId("string");
        user.setBook(book);

        when(bookService.findBookById(anyLong())).thenReturn(book);
        when(userService.findUserByEmail(anyString())).thenReturn(user);
        when(retrieveBookService.retrieveBookService(any(UserModel.class), any(BookModel.class))).thenReturn(user);

        UserModel result = retrieveBookPresenter.retrieveBookPresenter("test@example.com", 1L);

        assertNotNull(result);
        assertEquals(user, result);

        verify(bookService, times(1)).findBookById(1L);
        verify(userService, times(1)).findUserByEmail("test@example.com");
        verify(retrieveBookService, times(1)).retrieveBookService(user, book);
    }
}
