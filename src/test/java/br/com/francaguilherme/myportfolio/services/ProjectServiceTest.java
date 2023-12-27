package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectRepository repository;
    @InjectMocks
    private ProjectService service;

    @Test
    void testListProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());

        when(repository.findAll()).thenReturn(projects);

        List<Project> result = service.listProjects();

        assertEquals(projects, result);
    }

    @Test
    void testSaveProject() {
        Project project = new Project();

        when(repository.save(any())).thenReturn(project);

        Project result = service.saveProject(project);

        assertEquals(project, result);
    }

    @Test
    void testUpdateProject_validProject() {
        Project project = new Project();
        project.setId(1L);

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any())).thenReturn(project);

        Project result = service.updateProject(project);

        assertEquals(project, result);
    }

    @Test
    void testUpdateProject_invalidProject() {
        assertAll(
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateProject(createProjectWhitId(null))),
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateProject(createProjectWhitId(0L))),
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateProject(createProjectWhitId(1L)))
        );
    }

    @Test
    void testDeleteProject_validProject() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteProject(1L);

        verify(repository).deleteById(anyLong());
    }

    @Test
    void testDeleteProject_invalidProject() {
        when(repository.existsById(anyLong())).thenReturn(false);

        assertThrows(
                EntityNotFoundException.class,
                () -> service.deleteProject(1L));

        assertThrows(
                EntityNotFoundException.class,
                () -> service.deleteProject(0L));

        assertThrows(
                EntityNotFoundException.class,
                () -> service.deleteProject(null));
    }

    private Project createProjectWhitId(Long id) {
        Project project = new Project();
        project.setId(id);
        return project;
    }
}
