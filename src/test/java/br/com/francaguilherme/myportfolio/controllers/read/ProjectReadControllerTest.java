package br.com.francaguilherme.myportfolio.controllers.read;

import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProjectReadControllerTest {
    @Mock
    private ProjectService service;
    @InjectMocks
    private ProjectReadController controller;

    @Test
    void testListProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());

        when(service.listProjects()).thenReturn(projects);

        ResponseEntity<?> response = controller.listProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projects, response.getBody());

        verify(service, times(1)).listProjects();
    }

    @Test
    void testListProjects_NoContent() {
        when(service.listProjects()).thenReturn(null);

        ResponseEntity<?> response = controller.listProjects();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(service, times(1)).listProjects();
    }
}
