package br.com.francaguilherme.myportfolio.controllers.privateAccess;

import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminPasswordWrapper;
import br.com.francaguilherme.myportfolio.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class PrivateAdminController {
    @Autowired
    private AdminService service;

    @PutMapping
    public ResponseEntity<Void> setCredentials(@Valid @RequestBody AdminPasswordWrapper wrapper) {
        service.setCredentials(wrapper.getOldAdmin(), wrapper.getNewAdmin());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
