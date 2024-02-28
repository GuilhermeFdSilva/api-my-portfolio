package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.models.entities.Language;
import br.com.francaguilherme.myportfolio.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {
    @Mock
    private LanguageRepository repository;
    @Mock
    private AdminService adminService;

    @InjectMocks
    private LanguageService service;

    private final Language LANGUAGE = new Language();

    @BeforeEach
    void setUp() {
        LANGUAGE.setId(1L);
    }

    @Test
    void testListLanguages() {
        List<Language> languages = new ArrayList<>(List.of(LANGUAGE));

        when(repository.findAll()).thenReturn(languages);

        List<Language> response = service.listLanguages();

        assertAll(
                () -> {
                    for (int i = 0; i < response.size(); i++) {
                        assertNotNull(response.get(i));
                        assertEquals(languages.get(i).getId(), response.get(i).getId());
                    }
                }
        );
    }

    @Test
    void testListLanguagesEmptyList() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(EmptyListException.class, () -> service.listLanguages());
    }

    @Test
    void testSaveLanguage() {
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(repository.save(LANGUAGE)).thenReturn(LANGUAGE);

        service.saveLanguage(LANGUAGE, admin);

        verify(repository, times(1)).save(LANGUAGE);
    }

    @Test
    void testUpdateLanguage() {
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(LANGUAGE.getId())).thenReturn(true);
        when(repository.save(LANGUAGE)).thenReturn(LANGUAGE);

        Language response = service.updateLanguage(LANGUAGE, admin);

        assertEquals(LANGUAGE, response);
        verify(repository, times(1)).save(LANGUAGE);
    }

    @Test
    void testUpdateLanguageInvalid() {
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(LANGUAGE.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.updateLanguage(LANGUAGE, admin));
    }

    @Test
    void testDeleteLanguage() {
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteLanguage(1L, admin);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteLanguageInvalid() {
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(2L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.deleteLanguage(2L, admin));
    }
}
