package com.example.biblioteca.presenter;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.service.BookService;
import com.example.biblioteca.service.LendBookService;
import com.example.biblioteca.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LendBookPresenterTest {

    @Mock
    private LendBookService lendBookService;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private LendBookPresenter lendBookPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLendBookPresenter() {
        String email = "test@example.com";
        Long bookId = 1L;

        BookModel book = new BookModel();
        book.setId(bookId);
        book.setName("Test Book");
        book.setDescription("A test book description");
        book.setQuantity(5);

        UserModel user = new UserModel();
        user.setId("string");
        user.setName("Test User");
        user.setEmail(email);

        when(bookService.findBookById(bookId)).thenReturn(book);
        when(userService.findUserByEmail(email)).thenReturn(user);
        when(lendBookService.lendBookService(user, book)).thenReturn(user);

        UserModel updatedUser = lendBookPresenter.lendBookPresenter(email, bookId);

        assertEquals(user, updatedUser);
        verify(bookService, times(1)).findBookById(bookId);
        verify(userService, times(1)).findUserByEmail(email);
        verify(lendBookService, times(1)).lendBookService(user, book);
    }
}
