package br.com.francaguilherme.myportfolio.controllers.write;

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

@RestController
@RequestMapping("/admin/projects")
public class ProjectWriteController {
    @Autowired
    private ProjectService service;
    @Autowired
    private AdminService adminService;

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
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{Type}")
    public ResponseEntity<?> likeProject(
            @PathVariable String type,
            @RequestBody Project project) {
        try {
            validateType(type);

            if ("like".equals(type)) {
                project.setLikes(project.getLikes() + 1);
            } else if ("dislike".equals(type)) {
                project.setLikes(project.getLikes() - 1);
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
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateType(String type) {
        if (!"like".equals(type) && !"dislike".equals(type)) {
            throw new IllegalArgumentException();
        }
    }
}
