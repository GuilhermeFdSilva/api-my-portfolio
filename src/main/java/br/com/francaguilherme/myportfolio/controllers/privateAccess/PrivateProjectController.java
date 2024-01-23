package br.com.francaguilherme.myportfolio.controllers.privateAccess;

import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.models.entities.Project;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/projects")
public class PrivateProjectController {
    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<Project> saveProject(@Valid @RequestBody AdminWrapper<Project> wrapper) {
        Project project = service.saveProject(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Project> updateProject(@Valid @RequestBody AdminWrapper<Project> wrapper) {
        Project project = service.updateProject(wrapper.getType(), wrapper.getAdmin());
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        service.deleteProject(id, admin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
