package com.example.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.dto.AdmUserDto;
import com.example.biblioteca.presenter.LoginPresenter;
import com.example.biblioteca.security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/adm/login")
public class LoginController {

    @Autowired
    private LoginPresenter loginPresenter;

    @PostMapping()
    public ResponseEntity<JWTUtil> LoginPostController(@RequestBody AdmUserDto entity) throws Exception {
        return ResponseEntity.ok().body(loginPresenter.Login(entity));
    }

    
}
