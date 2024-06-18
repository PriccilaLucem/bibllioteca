package com.example.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.presenter.UserPresenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserPresenter userPresenter;

    @PostMapping()
    public ResponseEntity<UserModel> postUserController(@RequestBody UserDto entity) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(userPresenter.postUserPresenter(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> putMethodName(@PathVariable String id, @RequestBody UserDto entity) throws Exception{
        return ResponseEntity.accepted().body(userPresenter.putUserPresenter(id, entity));
    }
    
}
