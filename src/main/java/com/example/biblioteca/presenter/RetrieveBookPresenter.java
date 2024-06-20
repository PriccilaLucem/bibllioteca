package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.service.BookService;
import com.example.biblioteca.service.RetrieveBookService;
import com.example.biblioteca.service.UserService;


@Component
public class RetrieveBookPresenter {
    @Autowired
    private RetrieveBookService retrieveBookService;

    @Autowired 
    private BookService bookService;

    @Autowired 
    private UserService userService;

    public UserModel retrieveBookPresenter(String email, Long bookId){
        BookModel book = bookService.findBookById(bookId);
        UserModel user = userService.findUserByEmail(email);

        return retrieveBookService.retrieveBookService(user, book);
    } 
}
