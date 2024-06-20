package com.example.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.presenter.LendBookPresenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/lend")
public class LendBookController {
    
    @Autowired
    private LendBookPresenter lendBookPresenter;

    @PostMapping("/{user_email}/{book_id}")
    public ResponseEntity<UserModel> postMethodName(
        @PathVariable(value = "user_email") String userEmail, 
        @PathVariable(value = "book_id") Long bookId
        ){
            return ResponseEntity.ok().body(lendBookPresenter.lendBookPresenter(userEmail, bookId));
    }
    
}
