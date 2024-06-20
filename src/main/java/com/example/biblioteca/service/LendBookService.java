package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.repository.UserRepository;
import java.util.Date;

@Service
public class LendBookService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public UserModel lendBookService(UserModel user, BookModel book){
        book.setLendDate(new Date());
        user.setBook(book);
        book.setQuantity(book.getQuantity() -1);
        bookRepository.save(book);
        return userRepository.save(user);
    }
}
