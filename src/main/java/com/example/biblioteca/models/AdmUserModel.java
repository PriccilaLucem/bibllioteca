package com.example.biblioteca.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adm")
public class AdmUserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "email", nullable = false, length = 60 )
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;
    
    @Column(name = "is_adm", nullable = false)
    private Boolean isAdm;

    public AdmUserModel(){}

    public AdmUserModel(String email, String password, Boolean isAdm) {
        this.email = email;
        this.password = password;
        this.isAdm = isAdm;
    }
    public String getPassword() {
        return password;
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

}
