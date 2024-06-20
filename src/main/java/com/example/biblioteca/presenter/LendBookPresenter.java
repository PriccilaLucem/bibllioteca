package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.service.BookService;
import com.example.biblioteca.service.LendBookService;
import com.example.biblioteca.service.UserService;

@Component
public class LendBookPresenter {

    @Autowired
    private LendBookService lendBookService;

    @Autowired 
    private BookService bookService;

    @Autowired 
    private UserService userService;

    public UserModel lendBookPresenter(String email, Long bookId){
        BookModel book = bookService.findBookById(bookId);
        UserModel user = userService.findUserByEmail(email);

        return lendBookService.lendBookService(user, book);
    } 
}
