package com.example.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.dto.AdmUserDto;
import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.presenter.AdmUserPresenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/adm")
public class AdmUserController {
    
    @Autowired
    private AdmUserPresenter admUserPresenter;

    @PostMapping
    public ResponseEntity<AdmUserModel> postAdmUserController(@RequestBody AdmUserDto entity) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(admUserPresenter.postAdmUserPresenter(entity));
    }
    
    @PutMapping("/{id}")
    public  ResponseEntity<AdmUserModel> putAdmUserController(@PathVariable String id, @RequestBody AdmUserDto entity) throws Exception{
        return ResponseEntity.accepted().body(admUserPresenter.putAdmUserPresenter(id, entity));
    }
    
}
