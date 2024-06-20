package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.biblioteca.dto.BookDto;
import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.CategoryModel;
import com.example.biblioteca.service.BookService;
import com.example.biblioteca.service.CategoryService;
import com.example.biblioteca.util.Mapper;

import java.util.List;
@Component
public class BookPresenter {
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private BookService bookService;

    public BookModel postBookPresenter(BookDto bookDto){
        CategoryModel category = categoryService.findCategoryByIdService(bookDto.getCategoryId());
        BookModel book = Mapper.parseObject(bookDto, BookModel.class);
        book.setCategory(category);
        return bookService.createBookService(book);
    }

    public BookModel putBookPresenter(Long id, BookDto bookDto){
        CategoryModel category = categoryService.findCategoryByIdService(bookDto.getCategoryId());
        BookModel book = Mapper.parseObject(bookDto, BookModel.class);
        book.setCategory(category);
        return bookService.updateBook(id, book);
    }

    public BookModel getBookById(Long id){
        return bookService.findBookById(id);
    }

    public List<BookModel> getAllBooksPresenter(String search){
        return bookService.listAllBooks(search);
    }

}
