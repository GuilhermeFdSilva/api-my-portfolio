package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Language;
import br.com.francaguilherme.myportfolio.services.AdminService;
import br.com.francaguilherme.myportfolio.services.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/languages")
public class LanguageWriteController {
    @Autowired
    private LanguageService service;
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<?> saveLanguage(
            @RequestBody AdminWrapper<Language> wrapper) {
        try {
            if (adminService.validatePassword(wrapper.getAdmin().getPassword())) {
                Language newLanguage = service.saveLanguage(wrapper.getType());
                return new ResponseEntity<>(newLanguage, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Formato da requisição incorreto - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Informações dos objetos incorreta - " + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateLanguage(
            @RequestBody AdminWrapper<Language> wrapper) {
        try {
            if (adminService.validatePassword(wrapper.getAdmin().getPassword())){
                Language updatedLanguage = service.updateLanguage(wrapper.getType());
                return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Objeto não encontrado - " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Formato da requisição incorreto - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(
            @PathVariable Long id,
            @RequestBody Admin admin) {
        try {
            if (adminService.validatePassword(admin.getPassword())){
                service.deleteLanguage(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Objeto não encontrado - " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
