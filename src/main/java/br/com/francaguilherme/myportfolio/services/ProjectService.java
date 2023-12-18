package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    public List<Project> listProjects() {
        return repository.findAll();
    }

    public Project saveProject(Project project) {
        return repository.save(project);
    }

    public Project updateProject(Project project) {
        Long id = project.getId();

        if (id != null && id > 0 && repository.existsById(id)) {
            return repository.save(project);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteProject(Long id) {
        if (id != null && id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
