package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class BookService {
    @Autowired 
    private BookRepository bookRepository;

    public BookModel createBookService(BookModel book){
        book.setLendDate(null);
        return bookRepository.save(book);
    }

    public BookModel findBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }
    
    public List<BookModel> listAllBooks(String search){
        return bookRepository.findByTitleOrDescriptionOrCategoryName(search);
    }

    public BookModel updateBook(Long id, BookModel book){
        BookModel bookToBeUpdated = this.findBookById(id);
        bookToBeUpdated.setCategory(book.getCategory());
        bookToBeUpdated.setDescription(book.getDescription());
        bookToBeUpdated.setQuantity(book.getQuantity());
        book.setLendDate(null);
        return bookRepository.save(bookToBeUpdated);
    }

    public void deleteBookRepository(Long id){
        BookModel book = this.findBookById(id);
        bookRepository.delete(book);
    }
}
