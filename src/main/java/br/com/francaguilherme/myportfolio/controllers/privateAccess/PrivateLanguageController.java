package br.com.francaguilherme.myportfolio.controllers.privateAccess;

import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.services.LanguageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/languages")
public class PrivateLanguageController {
    @Autowired
    private LanguageService service;

    @PostMapping
    public ResponseEntity<Language> saveLanguage(@Valid @RequestBody AdminWrapper<Language> wrapper) {
        Language language = service.saveLanguage(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(language, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Language> updateLanguage(@Valid @RequestBody AdminWrapper<Language> wrapper) {
        Language language = service.updateLanguage(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        service.deleteLanguage(id, admin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
