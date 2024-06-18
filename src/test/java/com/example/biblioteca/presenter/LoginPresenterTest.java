package com.example.biblioteca.presenter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.security.JWTUtil;
import com.example.biblioteca.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginPresenterTest {

    @Captor
    private ArgumentCaptor<String> idCaptor;

    @Mock 
    private LoginService loginService;

    @InjectMocks
    private LoginPresenter loginPresenter;

    @Mock
    private JWTUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtUtil, "SECRET_KEY", "OIJQWODIJPQOIQDPIJWQIODIJWPOIWDOQOJIWDQODIQWIOWDOIOPIQD");
    }

    @Test
    public void TestLoginPresenter() throws Exception {
        AdmUserModel adm = new AdmUserModel("email@mail.com", "test", true);
        adm.setId("id");
        UserDto admDto = new UserDto("email@mail.com", "test");

        when(loginService.verifyAdmUserService(any(AdmUserModel.class))).thenReturn(adm);
        JWTUtil resultToken = loginPresenter.Login(admDto);

        assertThat(resultToken).isNotNull();
    }

}
