package com.example.biblioteca.presenter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.UserPresenter;
import com.example.biblioteca.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserPresenterTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserPresenter userPresenter;

    @Test
    public void postUserPresenterTest() throws Exception {
        UserDto userDto = new UserDto("test@test.com", "password");
        UserModel user = new UserModel("test@test.com", "password");
        
        when(userService.createUser(any(UserModel.class))).thenReturn(user);

        UserModel userCreated = userPresenter.postUserPresenter(userDto);

        assertThat(userCreated).isNotNull();
        assertThat(userCreated.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(userCreated.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(userCreated.getIsAdm()).isFalse();
    }


    @Test
    public void putUserPresenterTest() throws Exception{
        UserDto userDto = new UserDto("test@test.com", "password");
        UserModel user = new UserModel("test@test.com", "password");

        user.setId("id");

        when(userService.updateUser(anyString(), any(UserModel.class))).thenReturn(user);

        UserModel userCreated = userPresenter.putUserPresenter("id", userDto);

        assertThat(userCreated).isNotNull();
        assertThat(userCreated.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(userCreated.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(userCreated.getIsAdm()).isFalse();
    }
}
