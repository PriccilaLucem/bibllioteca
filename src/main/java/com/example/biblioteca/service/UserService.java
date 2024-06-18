package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.UserRepository;
import com.example.biblioteca.util.ValidateData;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    
    public UserModel createUser(UserModel user) throws Exception{
        String password = ValidateData.hashUserPasswordUtil(user.getPassword());
        ValidateData.verifyUserEmailUtil(user.getEmail());
        
        user.setIsAdm(false);
        user.setPassword(password);
        
        return userRepository.save(user);
    }

    public UserModel updateUser(String id, UserModel user) throws Exception{
        if(userRepository.findById(id).isPresent()){
            user.setId(id);
            ValidateData.verifyUserEmailUtil(user.getEmail());
            user.setPassword(ValidateData.hashUserPasswordUtil(user.getPassword()));
            return userRepository.save(user);
        }
        throw new EntityNotFoundException("User is invalid");
    }
    
    // public UserModel findAllUsers()

    
}
