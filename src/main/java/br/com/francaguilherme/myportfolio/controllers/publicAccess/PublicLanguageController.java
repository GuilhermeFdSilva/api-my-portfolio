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

@RestController
@RequestMapping("/public/languages")
public class PublicLanguageController {
    @Autowired
    private LanguageService service;

    @GetMapping
    public ResponseEntity<List<Language>> listLanguages() {
        List<Language> languages = service.listLanguages();
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }
}
