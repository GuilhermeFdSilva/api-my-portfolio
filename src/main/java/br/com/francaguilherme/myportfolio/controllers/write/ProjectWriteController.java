package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.services.AdminService;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por realizar operações de escrita de projetos.
 * Fornece endpoints para:
 * - Salvar um projeto;
 * - Atualizar um projeto;
 * - Dar like ou dislike em um projeto;
 * - Deletar um projeto.
 */
@RestController
@RequestMapping("/admin/projects")
public class ProjectWriteController {
    @Autowired
    private ProjectService service;
    @Autowired
    private AdminService adminService;

    /**
     * Salva um novo projeto
     *
     * @param wrapper Objeto {@link AdminWrapper} para receber a senha do administrador e o projeto.
     * @return O projeto salvo com seu ID.
     */
    @PostMapping
    public ResponseEntity<?> saveProject(
            @RequestBody AdminWrapper<Project> wrapper) {
        try {
            if (adminService.validatePassword(wrapper.getAdmin().getPassword())) {
                Project newProject = service.saveProject(wrapper.getType());
                return new ResponseEntity<>(newProject, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Formato da requisição incorreto - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Informações dos objetos incorreta - " + e.getMessage(), HttpStatus.CONFLICT);
        } catch (AdminNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Atualiza uma linguagem
     *
     * @param wrapper Objeto {@link AdminWrapper} para receber a senha do administrador e o projeto.
     * @return O projeto atualizado.
     */
    @PutMapping
    public ResponseEntity<?> updateProject(
            @RequestBody AdminWrapper<Project> wrapper) {
        try {
            if (adminService.validatePassword(wrapper.getAdmin().getPassword())) {
                Project updatedProject = service.updateProject(wrapper.getType());
                return new ResponseEntity<>(updatedProject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>("Objeto não encontrado - " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (AdminNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adiciona ou remove um like de um projeto.
     *
     * @param type    pathVariable que mostra as intenções da requisição, podendo receber:
     *                - like;
     *                - dislike.
     * @param project Projeto que recebera o like.
     * @return O projeto atualizado.
     */
    @PutMapping("/{Type}")
    public ResponseEntity<?> likeProject(
            @PathVariable String type,
            @RequestBody Project project) {
        try {
            validateType(type);

            if ("like".equals(type)) {
                project.likeProject();
            } else if ("dislike".equals(type)) {
                project.dislikeProject();
            }

            service.updateProject(project);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Objeto não encontrado - " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Formato da requisição incorreto - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deleta uma linguagem do sistema.
     *
     * @param id    OID do projeto a ser deletado
     * @param admin Objeto {@link Admin} para validar a senha.
     * @return A resposta do servidor para a requisição.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(
            @PathVariable Long id,
            @RequestBody Admin admin) {
        try {
            if (adminService.validatePassword(admin.getPassword())) {
                service.deleteProject(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Objeto não encontrado - " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (AdminNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Verifica se o tipo de like é valido.
     *
     * @param type Tipo de voto para ser verificado.
     */
    private void validateType(String type) {
        if (!"like".equals(type) && !"dislike".equals(type)) {
            throw new IllegalArgumentException();
        }
    }
}
