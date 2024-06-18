package com.example.biblioteca.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.exceptions.UnauthorizedException;
import com.example.biblioteca.repository.AdmUserRepository;
import com.example.biblioteca.util.ValidateData;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
    @Mock
    private AdmUserRepository admUserRepository;

    @Mock
    private AdmUserService admUserService;

    @InjectMocks
    private LoginService loginService;


    @Mock
    private ValidateData validateData;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    public void verifyAdmUserServiceTest() throws Exception {
        AdmUserModel adm = new AdmUserModel("test@mail.com", "test", true);
        when(validateData.verifyPasswordUserUtil(anyString(), anyString())).thenReturn(true);
        
        when(admUserService.getAdmUserByEmailService(anyString())).thenReturn(adm);

        AdmUserModel foundAdm = loginService.verifyAdmUserService(adm);
        assertThat(foundAdm).isNotNull();
        assertThat(foundAdm).isEqualTo(adm);

    }

    @Test
    public void verifyAdmUserServiceTestWithInvalidPassword() throws Exception {
        AdmUserModel adm = new AdmUserModel("test@mail.com", "password", true);

        when(admUserRepository.findByEmail(anyString())).thenReturn(Optional.of(adm));
        when(admUserService.getAdmUserByEmailService(anyString())).thenReturn(adm);

        assertThrows(UnauthorizedException.class, () -> {
            loginService.verifyAdmUserService(adm);
        });
    }
}
