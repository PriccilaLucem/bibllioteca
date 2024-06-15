package com.example.biblioteca.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.biblioteca.dto.AdmUserDto;
import com.example.biblioteca.presenter.LoginPresenter;
import com.example.biblioteca.security.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @MockBean
    private LoginPresenter loginPresenter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostLoginController() throws Exception {
        AdmUserDto admDto = new AdmUserDto("test@mail.com", "test");
        JWTUtil jwt = new JWTUtil("string");

        when(loginPresenter.Login(any(AdmUserDto.class))).thenReturn(jwt);

        mockMvc.perform(post("/api/adm/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admDto)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(jwt.getToken()));
    }
}
