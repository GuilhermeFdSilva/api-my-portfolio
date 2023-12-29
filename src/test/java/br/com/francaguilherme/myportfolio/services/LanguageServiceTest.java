package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @InjectMocks
    private LanguageService service;

    @Test
    void testListLanguages() {
        List<Language> languages = new ArrayList<>();
        languages.add(new Language());
        languages.add(new Language());

        when(repository.findAll()).thenReturn(languages);

        List<Language> result = service.listLanguages();

        assertEquals(languages, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSaveLanguage() {
        Language language = new Language();

        when(repository.save(any())).thenReturn(language);

        Language result = service.saveLanguage(language);

        assertEquals(language, result);
        verify(repository, times(1)).save(language);
    }

    @Test
    void testUpdateLanguage_validLanguage() {
        Language language = new Language();
        language.setId(1L);

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any())).thenReturn(language);

        Language result = service.updateLanguage(language);

        assertEquals(language, result);
        verify(repository, times(1)).save(language);
    }

    @Test
    void testUpdateLanguage_invalidLanguage() {
        assertAll(
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateLanguage(createLanguageWhitId(null))),
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateLanguage(createLanguageWhitId(0L))),
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateLanguage(createLanguageWhitId(1L)))
        );
        verify(repository, never()).save(any());
    }

    @Test
    void testDeleteLanguage_validLanguage() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteLanguage(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteLanguage_invalidLanguage() {
        when(repository.existsById(anyLong())).thenReturn(false);

        assertAll(
                () -> assertThrows(EntityNotFoundException.class, () -> service.deleteLanguage(1L)),
                () -> assertThrows(EntityNotFoundException.class, () -> service.deleteLanguage(0L)),
                () -> assertThrows(EntityNotFoundException.class, () -> service.deleteLanguage(null))
        );
        verify(repository, never()).deleteById(anyLong());
    }

    private Language createLanguageWhitId(Long id) {
        Language language = new Language();
        language.setId(id);
        return language;
    }
}
