package br.com.francaguilherme.myportfolio.controllers.read;

import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.services.LanguageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LanguageReadControllerTest {
    @Mock
    private LanguageService service;
    @InjectMocks
    private LanguageReadController controller;

    @Test
    void testListLanguages() {
        List<Language> languages = new ArrayList<>();
        languages.add(new Language());
        languages.add(new Language());

        when(service.listLanguages()).thenReturn(languages);

        ResponseEntity<?> response = controller.listLanguages();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(languages, response.getBody());

        verify(service, times(1)).listLanguages();
    }

    @Test
    void testListLanguages_NoContent() {
        when(service.listLanguages()).thenReturn(null);

        ResponseEntity<?> response = controller.listLanguages();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(service, times(1)).listLanguages();
    }
}
