package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     Serviço para manipulação e tratamento de dados da entidade {@link Language}. Esta classe utiliza os métodos do
 *     repositótio {@link LanguageRepository}, para ler, salvar, atualizar e deletar linguagens.
 * </p>
 *
 * <p>
 *     Caso ocorra algum problema, essa classe pode lançar {@link EntityNotFoundException}.
 * </p>
 *
 * <p>
 *     {@link Service} é utilizado oara que o Spring identifique que essa classe é um serviço, enquanto a anotação
 *     {@link Autowired} é utilizada para injeção de dependência do Spring, instanciando automaticamente
 *     {@link LanguageRepository}.
 * </p>
 *
 * @see Service
 * @see LanguageRepository
 * @see Language
 * @see Autowired
 * @see EntityNotFoundException
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
     * Atualiza uma linguagem do sistema.
     *
     * @param language Linguagem atualizada com ID.
     * @return A linguagem atualizada.
     * @throws EntityNotFoundException Caso o ID fornecido seja inválido.
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
     * @throws EntityNotFoundException Caso o ID fornecido seja inválido.
     */
    public void deleteLanguage(Long id) {
        if (id != null && id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
