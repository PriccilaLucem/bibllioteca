package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.util.ValidateData;
import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.exceptions.UnauthorizedException;

import jakarta.transaction.Transactional;

@Service
public class LoginService {
    
    @Autowired
    private AdmUserService admUserService;
    
    @Autowired
    private ValidateData validateData;

    @Transactional
    public AdmUserModel verifyAdmUserService(AdmUserModel adm) throws Exception{
        AdmUserModel foundAdm = admUserService.getAdmUserByEmailService(adm.getEmail());
        if(validateData.verifyPasswordUserUtil(adm.getPassword(), foundAdm.getPassword())){
            return foundAdm;    
        }
        throw new UnauthorizedException("Invalid password");
        
    }

}
