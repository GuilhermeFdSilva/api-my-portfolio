package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.models.Project;
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

    @PostMapping
    public ResponseEntity<Project> saveProject(@RequestBody Project project) {
        try {
            Project newProject = service.saveProject(project);
            return new ResponseEntity<>(newProject, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        try {
            Project updatedProject = service.updateProject(project);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{Type}")
    public ResponseEntity<Project> likeProject(
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        try {
            service.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateType(String type) {
        if (!"like".equals(type) && !"dislike".equals(type)) {
            throw new IllegalArgumentException();
        }
    }
}
