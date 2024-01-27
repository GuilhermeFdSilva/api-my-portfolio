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

/**
 * <p>
 *     Classe com endpoints privados para gerenciar a senha e login administrativos do sistema. Essa classe contém
 *     métodos que retornam {@link ResponseEntity} com o {@link HttpStatus} adequado a situação da requisição.
 * </p>
 *
 * <p>
 *     Essa classe utiliza as anotações {@link RestController} para mostrar ao Spring que essa classe se trata de um
 *     Controller, e {@link RequestMapping} para definir o path padrão da classe como /admin.
 * </p>
 *
 * <p>
 *     A classe contém o método {@link #setCredentials(AdminPasswordWrapper)}: Atualiza as credenciais do usuário
 *     administrador.
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 * @see PutMapping
 */
@RestController
@RequestMapping("/admin")
public class PrivateAdminController {
    @Autowired
    private AdminService service;

    /**
     * Atualiza as credenciais do usuário administrador.
     *
     * @param wrapper {@link AdminPasswordWrapper} contendo o admin antigo para validação e o novo, com as credenciais
     *                atualizadas.
     * @return {@link ResponseEntity} com {@link HttpStatus#OK}.
     */
    @PutMapping
    public ResponseEntity<Void> setCredentials(@Valid @RequestBody AdminPasswordWrapper wrapper) {
        service.setCredentials(wrapper.getOldAdmin(), wrapper.getNewAdmin());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
