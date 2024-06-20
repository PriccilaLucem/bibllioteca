package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
    @Autowired 
    private BookRepository bookRepository;

    public BookModel createBookService(BookModel book){

        return bookRepository.save(book);
    }

    public BookModel findBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }
    
    // public BookModel listAllBooks(){
        // return bookRepository
    // }

    public BookModel updateBook(Long id, BookModel book){
        BookModel bookToBeUpdated = this.findBookById(id);
        bookToBeUpdated.setCategory(book.getCategory());
        bookToBeUpdated.setDescription(book.getDescription());
        bookToBeUpdated.setQuantity(book.getQuantity());
        
        return bookRepository.save(bookToBeUpdated);
    }

    public void deleteBookRepository(Long id){
        BookModel book = this.findBookById(id);
        bookRepository.delete(book);
    }
}
