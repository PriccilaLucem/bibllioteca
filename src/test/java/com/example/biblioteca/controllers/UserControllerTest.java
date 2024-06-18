package com.example.biblioteca.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.models.UserModel;
import com.example.biblioteca.repository.UserPresenter;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPresenter userPresenter;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void postUserControllerTest() throws Exception {
        UserDto userDto = new UserDto("teste@test.com", "teste");
        UserModel user = new UserModel("teste@test.com", "teste");

        when(userPresenter.postUserPresenter(any(UserDto.class))).thenReturn(user);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.isAdm").value(false));
    }

    @Test
    public void putUserControllerTest() throws Exception{
        UserDto userDto = new UserDto("teste@test.com", "teste");
        UserModel user = new UserModel("teste@test.com", "teste");

        when(userPresenter.putUserPresenter(anyString(), any(UserDto.class))).thenReturn(user);

        mockMvc.perform(put("/api/user/{id}", "id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.isAdm").value(false));
    }

}
