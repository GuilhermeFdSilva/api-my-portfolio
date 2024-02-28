package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.models.DTOs.ProjectDTO;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.models.entities.Language;
import br.com.francaguilherme.myportfolio.models.entities.Project;
import br.com.francaguilherme.myportfolio.repositories.LanguageRepository;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectRepository repository;
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private AdminService adminService;

    @InjectMocks
    private ProjectService service;

    private final Project PROJECT = new Project();
    private final Language LANGUAGE = new Language();

    @BeforeEach
    void setUp() {
        PROJECT.setId(1L);
        LANGUAGE.setId(1L);

        PROJECT.setMain_language(LANGUAGE);
    }

    @Test
    void testListProjects() {
        List<Project> projects = new ArrayList<>(List.of(PROJECT));

        when(repository.findAll()).thenReturn(projects);

        List<ProjectDTO> projectDTOs = service.listProjects();

        assertAll(
                () -> {
                    for (int i = 0; i < projectDTOs.size(); i++) {
                        assertNotNull(projectDTOs.get(i));
                        assertEquals(projects.get(i).getId(), projectDTOs.get(i).getId());
                        assertEquals(projects.get(i).getMain_language().getId(), projectDTOs.get(i).getMain_language_id());
                    }
                }
        );
    }

    @Test
    void testListProjectsEmptyList() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(EmptyListException.class, () -> service.listProjects());
    }

    @Test
    void testSaveProject() {
        ProjectDTO entryDTO = ProjectDTO.toDTO(PROJECT);
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(languageRepository.findById(entryDTO.getMain_language_id())).thenReturn(Optional.of(LANGUAGE));
        when(repository.save(any())).thenReturn(PROJECT);

        ProjectDTO responseDTO = service.saveProject(entryDTO, admin);

        assertAll(
                () -> assertEquals(entryDTO.getId(), responseDTO.getId()),
                () -> assertEquals(entryDTO.getMain_language_id(), responseDTO.getMain_language_id())
        );
    }

    @Test
    void testSaveProjectInvalidLanguage() {
        PROJECT.setMain_language(new Language());
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);

        ProjectDTO entryDTO = ProjectDTO.toDTO(PROJECT);

        assertThrows(EntityNotFoundException.class, () -> service.saveProject(entryDTO, admin));
    }

    @Test
    void testUpdateProject() {
        Admin admin = new Admin();
        ProjectDTO entryDTO = ProjectDTO.toDTO(PROJECT);

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(entryDTO.getId())).thenReturn(true);
        when(languageRepository.findById(entryDTO.getMain_language_id())).thenReturn(Optional.of(LANGUAGE));
        when(repository.save(any())).thenReturn(PROJECT);

        ProjectDTO responseDTO = service.updateProject(entryDTO, admin);

        assertAll(
                () -> assertEquals(entryDTO.getId(), responseDTO.getId()),
                () -> assertEquals(entryDTO.getMain_language_id(), responseDTO.getMain_language_id())
        );
    }

    @Test
    void testUpdateProjectInvalid() {
        Admin admin = new Admin();
        ProjectDTO entryDTO = ProjectDTO.toDTO(PROJECT);

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(entryDTO.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.updateProject(entryDTO, admin));
    }

    @Test
    void testVoteProject() {
        ProjectDTO entryDTO = ProjectDTO.toDTO(PROJECT);

        when(repository.findById(entryDTO.getId())).thenReturn(Optional.of(PROJECT));
        when(repository.save(PROJECT)).thenReturn(PROJECT);

        assertAll(
                () -> assertEquals(0, service.voteProject(entryDTO, "dislike").getLikes()),
                () -> assertEquals(1, service.voteProject(entryDTO, "like").getLikes()),
                () -> assertEquals(0, service.voteProject(entryDTO, "dislike").getLikes())
        );
    }

    @Test
    void testDeleteProject() {
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteProject(1L, admin);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProjectInvalid() {
        Admin admin = new Admin();

        doNothing().when(adminService).validatePassword(admin);
        when(repository.existsById(2L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.deleteProject(2L, admin));

        verify(repository, never()).deleteById(2L);
    }
}