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

    @Column(name = "email", nullable = false, length = 60, unique=true)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "is_adm", nullable = false, updatable = false)
    private Boolean isAdm;

    public UserModel(){
        this.isAdm = false;
    }

    public UserModel(String email, String password){
        this.email = email;
        this.password = password;
        this.isAdm = false;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = true)
    private BookModel book;

    public BookModel getBook() {
        return book;
    }
    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }
    public Boolean getIsAdm() {
        return isAdm;
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
    public void setIsAdm(Boolean isAdm) {
        this.isAdm = isAdm;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }


}
