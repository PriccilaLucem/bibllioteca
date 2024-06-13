package com.example.biblioteca.repository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.biblioteca.models.AdmUserModel;

public class AdmUserRepositoryTest {
    @Captor
    private ArgumentCaptor<String> idCaptor;

    @Mock
    private AdmUserRepository admUserRepository;

    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByEmail(){

            AdmUserModel adm = new AdmUserModel("teste", "teste",true);
            when(admUserRepository.findByEmail(anyString())).thenReturn(Optional.of(adm));
    
            AdmUserModel foundAdmUserModel = admUserRepository.findByEmail(adm.getEmail()).get();

            assertThat(foundAdmUserModel).isNotNull();    
            assertThat(adm.getEmail()).isEqualTo(foundAdmUserModel.getEmail());
            assertThat(adm.getPassword()).isEqualTo(foundAdmUserModel.getPassword());
            assertThat(foundAdmUserModel.getIsAdm()).isTrue();
        }
}
