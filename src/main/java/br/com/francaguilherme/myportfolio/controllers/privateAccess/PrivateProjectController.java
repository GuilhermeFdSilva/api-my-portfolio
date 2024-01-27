package br.com.francaguilherme.myportfolio.controllers.privateAccess;

import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.DTOs.ProjectDTO;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *     Classe com endpoints privados para gerenciar os objetos {@link ProjectDTO} no sistema. Essa classe retorna
 *     {@link ResponseEntity} com ou sem um objeto {@link ProjectDTO} e o {@link HttpStatus} adequado para a resposta
 *     da requisição.
 * </p>
 *
 * <p>
 *     Essa classe utiliza as anotações {@link RestController} para mostrar ao Spring que essa classe se trata de um
 *     Controller, e {@link RequestMapping} para definir o path padrão da classe como /admin/projects.
 * </p>
 *
 * <p>
 *     Métodos da classe:
 *     <ul>
 *         <li>{@link #saveProject(AdminWrapper)}: Salva um novo projeto no sistema;</li>
 *         <li>{@link #updateProject(AdminWrapper)}: Atualiza um projeto do sistema;</li>
 *         <li>{@link #deleteProject(Long, Admin)}: Deleta uma linguagem do sistema.</li>
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
@RequestMapping("/admin/projects")
public class PrivateProjectController {
    @Autowired
    private ProjectService service;

    /**
     * Salva um novo projeto no sistema.
     *
     * @param wrapper {@link AdminWrapper} contendo um usuario {@link Admin} e um objeto {@link ProjectDTO}.
     * @return {@link ResponseEntity} com {@link HttpStatus#CREATED} e o projeto criado.
     */
    @PostMapping
    public ResponseEntity<ProjectDTO> saveProject(@Valid @RequestBody AdminWrapper<ProjectDTO> wrapper) {
        ProjectDTO project = service.saveProject(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    /**
     * Atualiza um projeto do sistema.
     *
     * @param wrapper {@link AdminWrapper} contendo um usuario {@link Admin} e um objeto {@link ProjectDTO}.
     * @return {@link ResponseEntity} com {@link HttpStatus#CREATED} e o projeto atualizado.
     */
    @PutMapping
    public ResponseEntity<ProjectDTO> updateProject(@Valid @RequestBody AdminWrapper<ProjectDTO> wrapper) {
        ProjectDTO project = service.updateProject(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * Deleta um projeto do sistema.
     *
     * @param id ID do projeto a ser deletado.
     * @param admin Usuario {@link Admin} que fez a solicitação.
     * @return {@link ResponseEntity} com {@link HttpStatus#NO_CONTENT}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        service.deleteProject(id, admin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
