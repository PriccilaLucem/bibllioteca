package com.example.biblioteca.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.repository.AdmUserRepository;
import org.junit.runner.RunWith;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AdmUserServiceTest {
    
    
    @Captor
    private ArgumentCaptor<String> idCaptor;

    @Mock
    private AdmUserRepository admUserRepository;

    @InjectMocks 
    private AdmUserService admUserService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAdmUserServiceValidDataTest() throws BadRequestException{
        AdmUserModel adm = new AdmUserModel("teste@test.com", "teste", true);
        when(admUserRepository.save(any(AdmUserModel.class))).thenReturn(adm);
        
        AdmUserModel createdAdm = admUserService.createAdmUserService(adm);
        
        assertThat(createdAdm).isNotNull(); 
        assertThat(createdAdm.getEmail()).isEqualTo("teste@test.com");
        assertThat(createdAdm.getIsAdm()).isTrue();

        verify(admUserRepository, times(1)).save(adm);
    }

    @Test
    public void getAdmUserModelServiceByEmailTest(){
        AdmUserModel adm = new AdmUserModel("teste@test.com", "teste", true);
        when(admUserRepository.findByEmail(anyString())).thenReturn(Optional.of(adm));    
        
        AdmUserModel foundAdm = admUserService.getAdmUserByEmailService(adm.getEmail());
        
        assertThat(foundAdm).isNotNull();
        assertThat(foundAdm.getEmail()).isEqualTo(adm.getEmail());
        assertThat(foundAdm.getIsAdm()).isTrue();

        verify(admUserRepository, times(1)).findByEmail(adm.getEmail());
    }
    
    @Test
    public void updateAdmUserServiceTest() throws BadRequestException {
        AdmUserModel existingAdm = new AdmUserModel("teste@test.com", "teste", true);
        existingAdm.setId("id");
        
        AdmUserModel admToUpdate = new AdmUserModel("test@test.com", "1234", true);
        admToUpdate.setId("id");

        when(admUserRepository.findById(anyString())).thenReturn(Optional.of(existingAdm));
        when(admUserRepository.save(any(AdmUserModel.class))).thenReturn(admToUpdate);

        AdmUserModel updatedAdm = admUserService.updateAdmUserService("id", admToUpdate);

        assertThat(updatedAdm).isNotNull();
        assertThat(updatedAdm.getEmail()).isEqualTo("test@test.com");
        assertThat(updatedAdm.getId()).isEqualTo("id");
        assertThat(updatedAdm.getIsAdm()).isTrue();

        ArgumentCaptor<AdmUserModel> captor = ArgumentCaptor.forClass(AdmUserModel.class);
       verify(admUserRepository, times(1)).save(captor.capture());
       
       AdmUserModel savedAdm = captor.getValue();
       assertThat(savedAdm.getEmail()).isEqualTo("test@test.com");
       assertThat(savedAdm.getIsAdm()).isTrue();
    }

}
