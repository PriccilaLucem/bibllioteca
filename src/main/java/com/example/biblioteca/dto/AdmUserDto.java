package com.example.biblioteca.dto;

public class AdmUserDto {
    private String email;
    private String password;
    private String hashCodeToCreateAdm;

    public AdmUserDto(){}

    public AdmUserDto(String email, String password){
        this.email = email;
        this.password = password;
    }


    public AdmUserDto(String email, String password, String hashCodeToCreateAdm){
        this.email = email;
        this.password = password;
        this.hashCodeToCreateAdm = hashCodeToCreateAdm;
    }
    public String getEmail() {
        return email;
    }
    public String getHashCodeToCreateAdm() {
        return hashCodeToCreateAdm;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setHashCodeToCreateAdm(String hashCodeToCreateAdm) {
        this.hashCodeToCreateAdm = hashCodeToCreateAdm;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
