package com.example.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.presenter.RetrieveBookPresenter;

@RestController
@RequestMapping("/api/retrieve")
public class RetrieveBookController {

    @Autowired
    private RetrieveBookPresenter retrieveBookPresenter;

    @PostMapping("/{user_email}/{book_id}")
    public ResponseEntity<UserModel> postMethodName(
        @PathVariable(value = "user_email") String userEmail, 
        @PathVariable(value = "book_id") Long bookId
        ){
            return ResponseEntity.ok().body(retrieveBookPresenter.retrieveBookPresenter(userEmail, bookId));
    }
}
