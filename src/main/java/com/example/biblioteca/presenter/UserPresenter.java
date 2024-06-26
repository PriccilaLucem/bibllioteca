package com.example.biblioteca.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.service.UserService;
import com.example.biblioteca.util.Mapper;


@Component
public class UserPresenter{


    @Autowired
    private UserService userService;

    public UserModel postUserPresenter(UserDto user) throws Exception{
        return userService.createUser(Mapper.parseObject(user, UserModel.class));
    }
    public UserModel putUserPresenter(String id, UserDto user) throws Exception{
        return userService.updateUser(id, Mapper.parseObject(user, UserModel.class));
    }
}