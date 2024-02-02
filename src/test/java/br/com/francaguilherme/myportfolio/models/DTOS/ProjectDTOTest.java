package br.com.francaguilherme.myportfolio.models.DTOS;

import br.com.francaguilherme.myportfolio.models.DTOs.ProjectDTO;
import br.com.francaguilherme.myportfolio.models.entities.Language;
import br.com.francaguilherme.myportfolio.models.entities.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectDTOTest {
    private ProjectDTO projectDTO;

    private final byte[] README = new byte[]{};
    private final String[] TOOLS = new String[]{};

    @BeforeEach
    void setUp() {
        projectDTO = new ProjectDTO();
    }

    @Test
    void setAndGetId() {
        assertNull(projectDTO.getId());

        projectDTO.setId(1L);

        assertEquals(1L, projectDTO.getId());
    }

    @Test
    void setAndGetTitle() {
        assertNull(projectDTO.getTitle());

        projectDTO.setTitle("title");

        assertEquals("title", projectDTO.getTitle());
    }

    @Test
    void setAndGetImage() {
        assertNull(projectDTO.getImage());

        projectDTO.setImage("image");

        assertEquals("image", projectDTO.getImage());
    }

    @Test
    void setAndGetDescription() {
        assertNull(projectDTO.getDescription());

        projectDTO.setDescription("desc");

        assertEquals("desc", projectDTO.getDescription());
    }

    @Test
    void setAndGetMainLanguageId() {
        assertNull(projectDTO.getMain_language_id());

        projectDTO.setMain_language_id(1L);

        assertEquals(1L, projectDTO.getMain_language_id());
    }

    @Test
    void setAndGetReadme() {
        assertNull(projectDTO.getReadme());

        projectDTO.setReadme(README);

        assertEquals(README, projectDTO.getReadme());
    }

    @Test
    void setAndGetLink_gh() {
        assertNull(projectDTO.getLink_gh());

        projectDTO.setLink_gh("link");

        assertEquals("link", projectDTO.getLink_gh());
    }

    @Test
    void setAndGetLink_pg() {
        assertNull(projectDTO.getLink_pg());

        projectDTO.setLink_pg("link");

        assertEquals("link", projectDTO.getLink_pg());
    }

    @Test
    void setAndGetTools() {
        assertNull(projectDTO.getTools());

        projectDTO.setTools(TOOLS);

        assertEquals(TOOLS, projectDTO.getTools());
    }

    @Test
    void setAndGetLikes() {
        assertEquals(0, projectDTO.getLikes());

        projectDTO.setLikes(10);

        assertEquals(10, projectDTO.getLikes());
    }

    @Test
    void testToDTO() {
        Language language = new Language();
        language.setId(1L);

        Project projectEntity = new Project();
        projectEntity.setId(1L);
        projectEntity.setTitle("title");
        projectEntity.setImage("image");
        projectEntity.setDescription("desc");
        projectEntity.setMain_language(language);
        projectEntity.setReadme(README);
        projectEntity.setLink_gh("gh");
        projectEntity.setLink_pg("pg");
        projectEntity.setTools(TOOLS);
        projectEntity.setLikes(10);

        projectDTO = ProjectDTO.toDTO(projectEntity);

        assertAll(
                () -> assertEquals(projectEntity.getId(), projectDTO.getId()),
                () -> assertEquals(projectEntity.getTitle(), projectDTO.getTitle()),
                () -> assertEquals(projectEntity.getImage(), projectDTO.getImage()),
                () -> assertEquals(projectEntity.getDescription(), projectDTO.getDescription()),
                () -> assertEquals(projectEntity.getMain_language().getId(), projectDTO.getMain_language_id()),
                () -> assertEquals(projectEntity.getReadme(), projectDTO.getReadme()),
                () -> assertEquals(projectEntity.getLink_gh(), projectDTO.getLink_gh()),
                () -> assertEquals(projectEntity.getLink_pg(), projectDTO.getLink_pg()),
                () -> assertEquals(projectEntity.getTools(), projectDTO.getTools()),
                () -> assertEquals(projectEntity.getLikes(), projectDTO.getLikes())
        );
    }

    @Test
    void testListToDTO() {
        List<Project> projects = new ArrayList<>();
        List<ProjectDTO> projectDTOs;

        for (long i = 0; i < 3; i++) {
            Project project = new Project();
            project.setId(i);
            project.setMain_language(new Language());

            projects.add(project);
        }

        projectDTOs = ProjectDTO.listToDTO(projects);

        assertAll(
                () -> {
                    for (int i = 0; i < projectDTOs.size(); i++) {
                        assertEquals(projects.get(i).getId(), projectDTOs.get(i).getId());
                    }
                }
        );
    }
}
