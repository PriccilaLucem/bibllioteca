package com.example.biblioteca.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.biblioteca.dto.BookDto;
import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.service.BookService;
import com.example.biblioteca.service.CategoryService;

class BookPresenterTest {

    @InjectMocks
    private BookPresenter bookPresenter;

    @Mock
    private CategoryService categoryService;

    @Mock
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostBookPresenter() {
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

        when(categoryService.findCategoryByIdService(1L)).thenReturn(category);
        when(bookService.createBookService(any(BookModel.class))).thenReturn(book);

        BookModel createdBook = bookPresenter.postBookPresenter(bookDto);

        assertEquals(book, createdBook);

        verify(categoryService, times(1)).findCategoryByIdService(1L);
        verify(bookService, times(1)).createBookService(any(BookModel.class));
    }

    @Test 
    void tetGetAllBooksPresenter(){
        BookModel book = new BookModel();
        book.setId(1L);
        book.setDescription("A book");
        book.setQuantity(10);

        when(bookService.listAllBooks(anyString())).thenReturn(List.of(book));

        List<BookModel> foundBooks = bookService.listAllBooks("");

        assertEquals(book, foundBooks.get(0));
        verify(bookService, times(1)).listAllBooks("");
    }

    @Test
    void testPutBookPresenter() {
        BookDto bookDto = new BookDto();
        bookDto.setCategoryId(1L);
        bookDto.setDescription("An updated book");
        bookDto.setQuantity(5);

        CategoryModel category = new CategoryModel();
        category.setId(1L);
        category.setName("Fiction");

        BookModel existingBook = new BookModel();
        existingBook.setId(1L);
        existingBook.setDescription("A new book");
        existingBook.setQuantity(10);
        existingBook.setCategory(category);

        BookModel updatedBook = new BookModel();
        updatedBook.setId(1L);
        updatedBook.setDescription("An updated book");
        updatedBook.setQuantity(5);
        updatedBook.setCategory(category);

        when(categoryService.findCategoryByIdService(1L)).thenReturn(category);
        when(bookService.updateBook(eq(1L), any(BookModel.class))).thenReturn(updatedBook);

        BookModel result = bookPresenter.putBookPresenter(1L, bookDto);

        assertEquals(updatedBook, result);

        verify(categoryService).findCategoryByIdService(1L);
        verify(bookService).updateBook(eq(1L), any(BookModel.class));
    }

    @Test
    void testGetBookById() {
        BookModel book = new BookModel();
        book.setId(1L);
        book.setDescription("A book");
        book.setQuantity(10);

        when(bookService.findBookById(1L)).thenReturn(book);

        BookModel foundBook = bookPresenter.getBookById(1L);

        assertEquals(book, foundBook);
        verify(bookService).findBookById(1L);
    }
}
