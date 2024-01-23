package br.com.francaguilherme.myportfolio.controllers.publicAccess;

import br.com.francaguilherme.myportfolio.models.DTOs.ProjectDTO;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/projects")
public class PublicProjectController {
    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> listProjects() {
        List<ProjectDTO> projects = service.listProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping("/{voteType}")
    public ResponseEntity<ProjectDTO> voteProject(@PathVariable String voteType, @Valid @RequestBody ProjectDTO project) {
        ProjectDTO projectVoted = service.voteProject(project, voteType);
        return new ResponseEntity<>(projectVoted, HttpStatus.OK);
    }
}
