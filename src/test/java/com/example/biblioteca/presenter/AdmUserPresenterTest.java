package com.example.biblioteca.presenter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.example.biblioteca.dto.UserDto;
import com.example.biblioteca.exceptions.UnauthorizedException;
import com.example.biblioteca.models.AdmUserModel;
import com.example.biblioteca.service.AdmUserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class AdmUserPresenterTest {

    @Mock
    private AdmUserService admUserService;

    @InjectMocks
    private AdmUserPresenter admUserPresenter;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(admUserPresenter, "hashCode", "expectedHashCode");
    }

    @Test
    public void testPostAdmUserPresenter_ValidHashCode() throws Exception {
        UserDto admUserDto = new UserDto("teste@test.com", "teste", "expectedHashCode");
        AdmUserModel admUserModel = new AdmUserModel("teste@test.com", "teste", true);

        when(admUserService.createAdmUserService(any(AdmUserModel.class))).thenReturn(admUserModel);

        AdmUserModel result = admUserPresenter.postAdmUserPresenter(admUserDto);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("teste@test.com");
        assertThat(result.getIsAdm()).isTrue();

        verify(admUserService, times(1)).createAdmUserService(any(AdmUserModel.class));
    }

    @Test
    public void testPostAdmUserPresenter_InvalidHashCode() throws Exception {
        UserDto admUserDto = new UserDto("teste@test.com", "teste", "invalidHashCode");

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            admUserPresenter.postAdmUserPresenter(admUserDto);
        });

        assertThat(exception.getMessage()).isEqualTo("Invalid hash code to create adm");

        verify(admUserService, times(0)).createAdmUserService(any(AdmUserModel.class));
    }

    @Test
    public void testPutAdmUserPresenter_ValidHashCode() throws Exception {
        UserDto admUserDto = new UserDto("teste@test.com", "teste", "expectedHashCode");
        AdmUserModel admUserModel = new AdmUserModel("teste@test.com", "teste", true);

        when(admUserService.updateAdmUserService(anyString(), any(AdmUserModel.class))).thenReturn(admUserModel);

        AdmUserModel result = admUserPresenter.putAdmUserPresenter("id", admUserDto);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("teste@test.com");
        assertThat(result.getIsAdm()).isTrue();

        verify(admUserService, times(1)).updateAdmUserService(anyString(), any(AdmUserModel.class));
    }

    @Test
    public void testPutAdmUserPresenter_InvalidHashCode() throws Exception {
        UserDto admUserDto = new UserDto("teste@test.com", "teste", "invalidHashCode");

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            admUserPresenter.putAdmUserPresenter("id", admUserDto);
        });

        assertThat(exception.getMessage()).isEqualTo("Invalid hash code to create adm");

        verify(admUserService, times(0)).updateAdmUserService(anyString(), any(AdmUserModel.class));
    }
}
