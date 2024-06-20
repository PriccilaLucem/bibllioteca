package com.example.biblioteca.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.UserRepository;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    

    @Captor
    private ArgumentCaptor<String> idCaptor;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserServiceTest() throws Exception{
        UserModel user = new UserModel("1234", "test@mail.com");

        when(userRepository.save(any(UserModel.class))).thenReturn(user);

        UserModel createdUser = userService.createUser(user);
        

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test 
    public void updateUserServiceTest() throws Exception{

        UserModel userTobeUpdated = new UserModel("123", "mail@mail.com");
        userTobeUpdated.setId("id");
        
        when(userRepository.save(any(UserModel.class))).thenReturn(userTobeUpdated);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(userTobeUpdated));
        
        UserModel updatedUser = userService.updateUser("id", userTobeUpdated);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getEmail()).isEqualTo(userTobeUpdated.getEmail());

        verify(userRepository, times(1)).save(userTobeUpdated);
    }
}
