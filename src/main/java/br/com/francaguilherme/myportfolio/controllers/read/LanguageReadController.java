package br.com.francaguilherme.myportfolio.controllers.read;

import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageReadController {
    @Autowired
    private LanguageService service;

    @GetMapping
    public ResponseEntity<List<Language>> listLanguages() {
        List<Language> languages = service.listLanguages();

        return (languages != null && !languages.isEmpty())
                ? new ResponseEntity<>(languages, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
