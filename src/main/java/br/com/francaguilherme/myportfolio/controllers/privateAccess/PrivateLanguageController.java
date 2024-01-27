package br.com.francaguilherme.myportfolio.controllers.privateAccess;

import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.models.entities.Language;
import br.com.francaguilherme.myportfolio.services.LanguageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *     Classe com endpoints privados para gerenciar os objetos {@link Language} no sistema. Essa classe retorna a
 *     {@link ResponseEntity} com ou sem um objeto {@link Language} e o {@link HttpStatus} adequado para a resposta da
 *     requisição.
 * </p>
 *
 * <p>
 *     Essa classe utiliza as anotações {@link RestController} para mostrar ao Spring que essa classe se trata de um
 *     Controller, e {@link RequestMapping} para definir o path padrão da classe como /admin/languages.
 * </p>
 *
 * <p>
 *     Métodos da classe:
 *     <ul>
 *         <li>{@link #saveLanguage(AdminWrapper)}: Salva uma nova linguagem no sistema;</li>
 *         <li>{@link #updateLanguage(AdminWrapper)}: Atualiza uma linguagem do sistema;</li>
 *         <li>{@link #deleteLanguage(Long, Admin)}: Deleta uma linguagem do sistema.</li>
 *     </ul>
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 * @see PostMapping
 * @see PutMapping
 * @see DeleteMapping
 */
@RestController
@RequestMapping("/admin/languages")
public class PrivateLanguageController {
    @Autowired
    private LanguageService service;

    /**
     * Salva uma nova linguagem no sistema.
     *
     * @param wrapper {@link AdminWrapper} contendo um usuario {@link Admin} e um objeto {@link Language}.
     * @return {@link ResponseEntity} com {@link HttpStatus#CREATED} e a linguagem criada.
     */
    @PostMapping
    public ResponseEntity<Language> saveLanguage(@Valid @RequestBody AdminWrapper<Language> wrapper) {
        Language language = service.saveLanguage(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(language, HttpStatus.CREATED);
    }

    /**
     * Atualiza uma linguagem do sistema.
     *
     * @param wrapper {@link AdminWrapper} contendo um usuario {@link Admin} e um objeto {@link Language}.
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e a linguagem atualizada.
     */
    @PutMapping
    public ResponseEntity<Language> updateLanguage(@Valid @RequestBody AdminWrapper<Language> wrapper) {
        Language language = service.updateLanguage(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    /**
     * Deleta uma linguagem do sistema.
     *
     * @param id ID da linguagem a ser deletada.
     * @param admin Usuario {@link Admin} que fez a solicitação.
     * @return {@link ResponseEntity} com {@link HttpStatus#NO_CONTENT}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        service.deleteLanguage(id, admin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
