package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminWrapper;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.services.AdminService;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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
public class ProjectWriteControllerTest {
    @Mock
    private ProjectService projectService;
    @Mock
    private AdminService adminService;
    @Mock
    private Project project;
    @InjectMocks
    private ProjectWriteController controller;
    private AdminWrapper<Project> wrapper;

    @BeforeEach
    void setUp() {
        Admin admin = new Admin();
        Project project = new Project();
        wrapper = new AdminWrapper<>(admin, project);
    }

    @Test
    void testSaveProject() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(projectService.saveProject(wrapper.getType())).thenReturn(wrapper.getType());

        ResponseEntity<?> response = controller.saveProject(wrapper);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(wrapper.getType(), response.getBody());

        verify(projectService, times(1)).saveProject(wrapper.getType());
    }

    @Test
    void testSaveProject_invalidPassword() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(false);

        ResponseEntity<?> response = controller.saveProject(wrapper);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor", response.getBody());

        verify(projectService, never()).saveProject(any());
    }

    @Test
    void testSaveProject_IllegalArgument() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(projectService.saveProject(wrapper.getType())).thenThrow(new IllegalArgumentException());

        ResponseEntity<?> response = controller.saveProject(wrapper);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Formato da requisição incorreto - " + new IllegalArgumentException().getMessage(), response.getBody());

        verify(projectService, times(1)).saveProject(wrapper.getType());
    }

    @Test
    void testSaveProject_AdminNotFound() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.saveProject(wrapper);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new AdminNotFoundException().getMessage(), response.getBody());

        verify(projectService, never()).saveProject(any());
    }

    @Test
    void testUpdateProject() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(projectService.updateProject(wrapper.getType())).thenReturn(wrapper.getType());

        ResponseEntity<?> response = controller.updateProject(wrapper);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wrapper.getType(), response.getBody());

        verify(projectService, times(1)).updateProject(wrapper.getType());
    }

    @Test
    void testUpdateProject_InvalidPassword() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(false);

        ResponseEntity<?> response = controller.updateProject(wrapper);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor", response.getBody());
        verify(projectService, never()).updateProject(any());
    }

    @Test
    void testUpdateProject_EntityNotFound() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(projectService.updateProject(wrapper.getType())).thenThrow(new EntityNotFoundException());

        ResponseEntity<?> response = controller.updateProject(wrapper);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Objeto não encontrado - " + new EntityNotFoundException().getMessage(), response.getBody());

        verify(projectService, times(1)).updateProject(wrapper.getType());
    }

    @Test
    void testUpdateProject_IllegalArgument() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenReturn(true);
        when(projectService.updateProject(wrapper.getType())).thenThrow(new IllegalArgumentException());

        ResponseEntity<?> response = controller.updateProject(wrapper);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Formato da requisição incorreto - " + new IllegalArgumentException().getMessage(), response.getBody());

        verify(projectService, times(1)).updateProject(wrapper.getType());
    }

    @Test
    void testUpdateProject_AdminNotFound() {
        when(adminService.validatePassword(wrapper.getAdmin().getPassword())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.updateProject(wrapper);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new AdminNotFoundException().getMessage(), response.getBody());
        verify(projectService, never()).updateProject(wrapper.getType());
    }

    @Test
    void testLikeProject() {
        List<String> requests = new ArrayList<>(List.of("like", "dislike"));
        List<ResponseEntity<?>> responses = new ArrayList<>();

        when(projectService.updateProject(project)).thenReturn(project);

        requests.forEach((request) -> responses.add(controller.likeProject(request, project)));

        assertAll(() -> responses.forEach((response) -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(project, response.getBody());
        }));
        verify(project, times(1)).likeProject();
        verify(project, times(1)).dislikeProject();
    }

    @Test
    void testLikeProject_EntityNotFound() {
        when(projectService.updateProject(any())).thenThrow(new EntityNotFoundException());

        ResponseEntity<?> response = controller.likeProject("like", project);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Objeto não encontrado - " + new EntityNotFoundException().getMessage(), response.getBody());

        verify(projectService, times(1)).updateProject(project);
    }

    @Test
    void testLikeProject_IllegalArgument() {
        ResponseEntity<?> response = controller.likeProject("invalidArg", project);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Formato da requisição incorreto - " + new IllegalArgumentException().getMessage(), response.getBody());
    }

    @Test
    void testLikeProject_RuntimeException() {
        when(projectService.updateProject(any())).thenThrow(new RuntimeException());

        ResponseEntity<?> response = controller.likeProject("like", project);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new RuntimeException().getMessage(), response.getBody());

        verify(projectService, times(1)).updateProject(project);
    }

    @Test
    void testDeleteProject() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenReturn(true);

        ResponseEntity<?> response = controller.deleteProject(1L, admin);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(projectService, times(1)).deleteProject(1L);
    }

    @Test
    void testDeleteProject_invalidPassword() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenReturn(false);

        ResponseEntity<?> response = controller.deleteProject(1L, admin);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor", response.getBody());

        verify(projectService, never()).deleteProject(anyLong());
    }

    @Test
    void testDeleteProject_EntityNotFound() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenThrow(new EntityNotFoundException());

        ResponseEntity<?> response = controller.deleteProject(null, admin);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Objeto não encontrado - " + new EntityNotFoundException().getMessage(), response.getBody());

        verify(projectService, never()).deleteProject(anyLong());
    }

    @Test
    void testDeleteProject_AdminNotFound() {
        Admin admin = new Admin();

        when(adminService.validatePassword(admin.getPassword())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.deleteProject(1L, admin);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new AdminNotFoundException().getMessage(), response.getBody());

        verify(projectService, never()).deleteProject(anyLong());
    }
}
