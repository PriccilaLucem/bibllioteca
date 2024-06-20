package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.exceptions.UnauthorizedException;
import com.example.biblioteca.service.AdmUserService;
import com.example.biblioteca.util.Mapper;


@Component
public class AdmUserPresenter {
    @Value("${ADM_HASH_CODE}")
    private String hashCode;
    
    @Autowired
    private AdmUserService admUserService;

    public AdmUserModel postAdmUserPresenter(UserDto adm) throws Exception{
        if(adm.getHashCodeToCreateAdm() == this.hashCode){
           return admUserService.createAdmUserService(Mapper.parseObject(adm, AdmUserModel.class));
        }
        throw new UnauthorizedException("Invalid hash code to create adm");
    }
    
    public AdmUserModel putAdmUserPresenter(String id, UserDto adm) throws Exception{
        if(adm.getHashCodeToCreateAdm() == this.hashCode){
           return admUserService.updateAdmUserService(id,Mapper.parseObject(adm, AdmUserModel.class));
        }
        throw new UnauthorizedException("Invalid hash code to create adm");
    }
}
