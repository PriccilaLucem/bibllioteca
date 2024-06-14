package com.example.biblioteca.exceptions;

public class UnauthorizedException extends Exception{

    public UnauthorizedException(String msg){
        super(msg);
    }
}