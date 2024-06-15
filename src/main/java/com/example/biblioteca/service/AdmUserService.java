package com.example.biblioteca.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.repository.AdmUserRepository;

import jakarta.persistence.EntityNotFoundException;
import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class AdmUserService {
    
    @Autowired
    private AdmUserRepository admUserRepository;
    
    public AdmUserModel createAdmUserService(AdmUserModel adm) throws BadRequestException{
        verifyAdmUserEmailService(adm.getEmail());
        adm.setPassword(hashAdmUserPasswordService(adm.getPassword()));
        adm.setIsAdm(true);
        return this.admUserRepository.save(adm);
        
    }

    public AdmUserModel getAdmUserByEmailService(String email){
        return this.admUserRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Incorrect email"));
    }

    public AdmUserModel updateAdmUserService(String id, AdmUserModel adm) throws BadRequestException{
        verifyAdmUserEmailService(adm.getEmail());

        if(admUserRepository.findById(id).isPresent()){
            
            verifyAdmUserEmailService(adm.getEmail());
            String password = hashAdmUserPasswordService(adm.getPassword());

            AdmUserModel admToput = new AdmUserModel(adm.getEmail(), password, true);

            return this.admUserRepository.save(admToput);
        }
        throw new EntityNotFoundException("Adm is invalid");
    }
    public boolean verifyPasswordAdmUserService(String password, String hashedPassowrd){
        return BCrypt.verifyer().verify(password.toCharArray(), password).verified;
    }
    private String hashAdmUserPasswordService(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    private void verifyAdmUserEmailService(String email) throws BadRequestException{
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(!email.matches(regex)){
            throw new BadRequestException("Invalid Email");
        }

    }
}

