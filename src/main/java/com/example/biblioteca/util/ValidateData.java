package com.example.biblioteca.util;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
@Service
public class ValidateData {
        
    public boolean verifyPasswordUserUtil(String password, String hashedPassowrd){
        return BCrypt.verifyer().verify(password.toCharArray(), password).verified;
    }

    public String hashUserPasswordUtil(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public void verifyUserEmailUtil(String email) throws BadRequestException{
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(!email.matches(regex)){
            throw new BadRequestException("Invalid Email");
        }

    }
}
