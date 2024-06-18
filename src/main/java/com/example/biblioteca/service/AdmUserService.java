package com.example.biblioteca.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.repository.AdmUserRepository;
import com.example.biblioteca.util.ValidateData;

import jakarta.persistence.EntityNotFoundException;
@Service
public class AdmUserService {
    
    @Autowired
    private AdmUserRepository admUserRepository;
    
    public AdmUserModel createAdmUserService(AdmUserModel adm) throws BadRequestException{
        ValidateData.verifyUserEmailUtil(adm.getEmail());
        adm.setPassword(ValidateData.hashUserPasswordUtil(adm.getPassword()));
        adm.setIsAdm(true);
        return this.admUserRepository.save(adm);
        
    }

    public AdmUserModel getAdmUserByEmailService(String email){
        return this.admUserRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Incorrect email"));
    }

    public AdmUserModel updateAdmUserService(String id, AdmUserModel adm) throws BadRequestException{
        ValidateData.verifyUserEmailUtil(adm.getEmail());

        if(admUserRepository.findById(id).isPresent()){
            
            String password = ValidateData.hashUserPasswordUtil(adm.getPassword());

            AdmUserModel admToput = new AdmUserModel(adm.getEmail(), password, true);

            return this.admUserRepository.save(admToput);
        }
        throw new EntityNotFoundException("Adm is invalid");
    }
}

