package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.services.AdminService;
import br.com.francaguilherme.myportfolio.services.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LanguageWriteControllerTest {
    @Mock
    private LanguageService languageService;
    @Mock
    private AdminService adminService;
    @InjectMocks
    private LanguageWriteController controller;
    private AdminWrapper<Language> wrapper;

    @BeforeEach
    void setUp() {
        Admin admin = new Admin();
        Language language = new Language();
        wrapper = new AdminWrapper<>(admin, language);
    }

    @Test
    void testSaveLanguage() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(languageService.saveLanguage(wrapper.getType())).thenReturn(wrapper.getType());

        ResponseEntity<?> response = controller.saveLanguage(wrapper);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(wrapper.getType(), response.getBody());

        verify(languageService, times(1)).saveLanguage(wrapper.getType());
    }

    @Test
    void testSaveLanguage_invalidPassword() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(false);

        ResponseEntity<?> response = controller.saveLanguage(wrapper);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor", response.getBody());

        verify(languageService, never()).saveLanguage(any());
    }

    @Test
    void testSaveLanguage_IllegalArgument() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(languageService.saveLanguage(wrapper.getType())).thenThrow(new IllegalArgumentException());

        ResponseEntity<?> response = controller.saveLanguage(wrapper);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Formato da requisição incorreto - " + new IllegalArgumentException().getMessage(), response.getBody());

        verify(languageService, times(1)).saveLanguage(any());
    }

    @Test
    void testSaveLanguage_AdminNotFound() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.saveLanguage(wrapper);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new AdminNotFoundException().getMessage(), response.getBody());

        verify(languageService, never()).saveLanguage(any());
    }

    @Test
    void testUpdateLanguage() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(languageService.updateLanguage(wrapper.getType())).thenReturn(wrapper.getType());

        ResponseEntity<?> response = controller.updateLanguage(wrapper);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wrapper.getType(), response.getBody());

        verify(languageService, times(1)).updateLanguage(wrapper.getType());
    }

    @Test
    void testUpdateLanguage_invalidPassword() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(false);

        ResponseEntity<?> response = controller.updateLanguage(wrapper);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor", response.getBody());

        verify(languageService, never()).updateLanguage(any());
    }

    @Test
    void testUpdateLanguage_EntityNotFound() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(languageService.updateLanguage(wrapper.getType())).thenThrow(new EntityNotFoundException());

        ResponseEntity<?> response = controller.updateLanguage(wrapper);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Objeto não encontrado - " + new EntityNotFoundException().getMessage(), response.getBody());

        verify(languageService, times(1)).updateLanguage(wrapper.getType());
    }

    @Test
    void testUpdateLanguage_IllegalArgument() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(languageService.updateLanguage(wrapper.getType())).thenThrow(new IllegalArgumentException());

        ResponseEntity<?> response = controller.updateLanguage(wrapper);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Formato da requisição incorreto - " + new IllegalArgumentException().getMessage(), response.getBody());

        verify(languageService, times(1)).updateLanguage(wrapper.getType());
    }

    @Test
    void testUpdateLanguage_AdminNotFound() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.updateLanguage(wrapper);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new AdminNotFoundException().getMessage(), response.getBody());

        verify(languageService, never()).updateLanguage(any());
    }

    @Test
    void testDeleteLanguage() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenReturn(true);

        ResponseEntity<?> response = controller.deleteLanguage(1L, admin);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(languageService, times(1)).deleteLanguage(1L);
    }

    @Test
    void testDeleteLanguage_invalidPassword() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenReturn(false);

        ResponseEntity<?> response = controller.deleteLanguage(1L, admin);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor", response.getBody());

        verify(languageService, never()).deleteLanguage(anyLong());
    }

    @Test
    void testDeleteLanguage_EntityNotFound() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenThrow(new EntityNotFoundException());

        ResponseEntity<?> response = controller.deleteLanguage(null, admin);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Objeto não encontrado - " + new EntityNotFoundException().getMessage(), response.getBody());

        verify(languageService, never()).deleteLanguage(anyLong());
    }

    @Test
    void testDeleteLanguage_AdminNotFound() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.deleteLanguage(1L, admin);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new AdminNotFoundException().getMessage(), response.getBody());

        verify(languageService, never()).deleteLanguage(anyLong());
    }
}
