package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository repository;

    public List<Language> listLanguages() {
        return repository.findAll();
    }

    public Language saveLanguage(Language language) {
        return repository.save(language);
    }

    public Language updateLanguage(Language language) {
        Long id = language.getId();

        if (id != null && id > 0 && repository.existsById(id)) {
            return repository.save(language);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteLanguage(Long id) {
        if (id != null && id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
