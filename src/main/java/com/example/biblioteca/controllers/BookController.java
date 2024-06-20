package com.example.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.dto.BookDto;
import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.presenter.BookPresenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    
    @Autowired
    private BookPresenter bookPresenter;

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getOneBookController(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(bookPresenter.getBookById(id));
    }

    @PostMapping()
    public ResponseEntity<BookModel> postBookController(@RequestBody BookDto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookPresenter.postBookPresenter(entity));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BookModel> putBookController(@PathVariable Long id, @RequestBody BookDto entity) {
        return ResponseEntity.accepted().body(bookPresenter.putBookPresenter(id, entity)) ;
    }
    
    @GetMapping()
    public ResponseEntity<List<BookModel>> getAllBooks(@RequestParam(required = false) String search) {
        return ResponseEntity.ok().body(bookPresenter.getAllBooksPresenter(search));
    }
    
    
}
