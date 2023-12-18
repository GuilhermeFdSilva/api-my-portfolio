package br.com.francaguilherme.myportfolio.controllers.read;

import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectReadController {
    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<Project>> listProjects() {
        List<Project> projects = service.listProjects();

        return (projects != null && !projects.isEmpty())
                ? new ResponseEntity<>(projects, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
