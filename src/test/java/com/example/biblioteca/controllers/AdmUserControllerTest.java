package com.example.biblioteca.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.presenter.AdmUserPresenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.biblioteca.dto.AdmUserDto;

@WebMvcTest(AdmUserController.class)
public class AdmUserControllerTest {
    

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdmUserPresenter admUserPresenter;
    
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
    
        
    @Test
    public void testPostAdmUserController() throws Exception {
        AdmUserModel admUserModel = new AdmUserModel("teste@test.com", "teste", true);
        AdmUserDto admUserDto = new AdmUserDto("teste@test.com", "teste", "expectedHashCode");

        when(admUserPresenter.postAdmUserPresenter(any(AdmUserDto.class))).thenReturn(admUserModel);

        mockMvc.perform(post("/api/adm")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(admUserDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.email").value("teste@test.com"))
        .andExpect(jsonPath("$.isAdm").value(true));
    }

    @Test
    public void testPutAdmUserController() throws Exception {
        AdmUserDto admUserDto = new AdmUserDto("teste@test.com", "teste", "expectedHashCode");
        AdmUserModel admUserModel = new AdmUserModel("teste@test.com", "teste", true);
        when(admUserPresenter.putAdmUserPresenter(anyString(), any(AdmUserDto.class))).thenReturn(admUserModel);
        mockMvc.perform(put("/api/adm/{id}", "id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admUserDto)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.email").value("teste@test.com"))
                .andExpect(jsonPath("$.isAdm").value(true));

    }   
}
