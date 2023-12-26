package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para operações relacionadas a entidade {@link Language}
 * Este serviço fornece métodos para:
 *  - Listar as linguagens;
 *  - Salvar uma linguagem;
 *  - Atualizar uma linguagem;
 *  - Deletar uma linguagem.
 */
@Service
public class LanguageService {
    @Autowired
    private LanguageRepository repository;

    /**
     * Retorna a lista de todas as linguagens armazenadas no sistema.
     *
     * @return Lista de todas as linguagens.
     */
    public List<Language> listLanguages() {
        return repository.findAll();
    }

    /**
     * Salva uma nova linguagem.
     *
     * @param language Linguagem a ser salva.
     * @return A linguagem salva com seu ID.
     */
    public Language saveLanguage(Language language) {
        return repository.save(language);
    }

    /**
     * Atualiza uma linguagem
     *
     * @param language Linguagem atualizada com ID.
     * @return A linguagem atualizada.
     */
    public Language updateLanguage(Language language) {
        Long id = language.getId();

        if (id != null && id > 0 && repository.existsById(id)) {
            return repository.save(language);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Deleta uma linguagem do sistema
     *
     * @param id O ID da linguagem a ser deletada.
     */
    public void deleteLanguage(Long id) {
        if (id != null && id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
