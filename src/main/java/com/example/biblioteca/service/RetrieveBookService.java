package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.repository.UserRepository;

@Service
public class RetrieveBookService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public UserModel retrieveBookService(UserModel user, BookModel book){
        user.setBook(null);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        return userRepository.save(user);
    }
}