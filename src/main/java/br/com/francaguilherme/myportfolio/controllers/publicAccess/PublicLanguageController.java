package br.com.francaguilherme.myportfolio.controllers.publicAccess;

import br.com.francaguilherme.myportfolio.models.entities.Language;
import br.com.francaguilherme.myportfolio.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *     Classe com endpoints públicos para leitura da lista de {@link Language} salva no sistema. Essa classe retorna a
 *     {@link ResponseEntity} com a lista de linguagens e o {@link HttpStatus} adequado.
 * </p>
 *
 * <p>
 *     Essa classe utiliza as anotações {@link RestController} para mostrar ao Spring que essa classe se trata de um
 *     Controller, e {@link RequestMapping} para definir o path padrão da classe como /public/languages.
 * </p>
 *
 * <p>
 *     Esta classe contém somente um método:
 *     <ul>
 *         <li>{@link #listLanguages()}: Lista todas as linguagens do sistema.</li>
 *     </ul>
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 * @see GetMapping
 */
@RestController
@RequestMapping("/public/languages")
public class PublicLanguageController {
    @Autowired
    private LanguageService service;

    /**
     * Lista todas as linguagens do sistema.
     *
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e a lista de linguagens.
     */
    @GetMapping
    public ResponseEntity<List<Language>> listLanguages() {
        List<Language> languages = service.listLanguages();
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }
}
