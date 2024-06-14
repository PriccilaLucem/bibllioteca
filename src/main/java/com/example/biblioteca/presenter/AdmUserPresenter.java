package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.biblioteca.dto.AdmUserDto;
import com.example.biblioteca.exceptions.UnauthorizedException;
import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.service.AdmUserService;
import com.example.biblioteca.util.Mapper;


@Service
public class AdmUserPresenter {
    @Value("${ADM_HASH_CODE}")
    private String hashCode;
    
    @Autowired
    private AdmUserService admUserService;

    public AdmUserModel postAdmUserPresenter(AdmUserDto adm) throws Exception{
        if(adm.getHashCodeToCreateAdm() == this.hashCode){
           return admUserService.createAdmUserService(Mapper.parseObject(adm, AdmUserModel.class));
        }
        throw new UnauthorizedException("Invalid hash code to create adm");
    }
    
    public AdmUserModel putAdmUserPresenter(String id, AdmUserDto adm) throws Exception{
        if(adm.getHashCodeToCreateAdm() == this.hashCode){
           return admUserService.updateAdmUserService(id,Mapper.parseObject(adm, AdmUserModel.class));
        }
        throw new UnauthorizedException("Invalid hash code to create adm");
    }
}
