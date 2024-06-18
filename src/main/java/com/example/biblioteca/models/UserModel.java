package com.example.biblioteca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserModel extends AdmUserModel {

    public UserModel() {
        super();
        setIsAdm(false);
    }
    
    public UserModel(String email, String password){
        super(email, password, false);
    }
}
