package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidPasswordException;
import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminPasswordWrapper;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por realizar operações de escrita de administrador.
 * Fornece endpoints para atualizar a senha do administrador.
 */
@RestController
@RequestMapping("/admin")
public class AdminWriteController {
    @Autowired
    private AdminService service;

    /**
     * Atualiza a senha do administrador no sistema.
     *
     * @param wrapper Objeto {@link AdminPasswordWrapper} para receber a senha antiga e nova.
     * @return O objeto {@link Admin} atualizado com a nova senha.
     */
    @PutMapping
    public ResponseEntity<?> setPassword(
            @RequestBody AdminPasswordWrapper wrapper) {
        try {
            Admin upToDate = service.setPassword(
                    wrapper.getOldPassword().getPassword(),
                    wrapper.getNewPassword().getPassword());
            return new ResponseEntity<>(upToDate, HttpStatus.OK);
        } catch (InvalidPasswordException e) {
            return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
