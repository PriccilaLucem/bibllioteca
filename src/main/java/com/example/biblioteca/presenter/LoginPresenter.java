package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.security.JWTUtil;
import com.example.biblioteca.service.LoginService;
import com.example.biblioteca.util.Mapper;

@Component
public class LoginPresenter {
    
    @Autowired
    private LoginService loginService;


    public JWTUtil Login(UserDto adm) throws Exception{
        AdmUserModel admToGenToken = loginService.verifyAdmUserService(Mapper.parseObject(adm, AdmUserModel.class));
        String token = new JWTUtil().generateToken(admToGenToken); 
        return new JWTUtil(token);
    }
}
