package com.example.biblioteca.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, length = 60, unique=true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = true)
    private BookModel book;

    public UserModel(){}

    public UserModel(String email, String name){
        this.email = email;
        this.name = name;

    }

    public BookModel getBook() {
        return book;
    }
    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBook(BookModel book) {
        this.book = book;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(String id) {
        this.id = id;
    }
}
