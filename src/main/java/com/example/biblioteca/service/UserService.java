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
    
    @Autowired
    private ValidateData validateData;

    public UserModel createUser(UserModel user) throws Exception{
        validateData.verifyUserEmailUtil(user.getEmail());
        
        
        return userRepository.save(user);
    }

    public UserModel updateUser(String id, UserModel user) throws Exception{
        if(userRepository.findById(id).isPresent()){
            user.setId(id);
            validateData.verifyUserEmailUtil(user.getEmail());
            return userRepository.save(user);
        }
        throw new EntityNotFoundException("User is invalid");
    }
    
    public UserModel findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    // public UserModel findAllUsers()

    
}
