package br.com.francaguilherme.myportfolio.models.entities;

import br.com.francaguilherme.myportfolio.models.DTOs.ProjectDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectTest {
    private Project project;
    private final Language LANGUAGE = new Language();
    private final byte[] README = new byte[]{};
    private final String[] TOOLS = new String[]{};

    @BeforeEach
    void setUp() {
        project = new Project();
    }

    @Test
    void testGeAndSetId() {
        assertNull(project.getId());

        project.setId(1L);

        assertEquals(1L, project.getId());
    }

    @Test
    void testGeAndSetTitle() {
        assertNull(project.getTitle());

        project.setTitle("title");

        assertEquals("title", project.getTitle());
    }

    @Test
    void testGeAndSetImage() {
        assertNull(project.getImage());

        project.setImage("image");

        assertEquals("image", project.getImage());
    }

    @Test
    void testGeAndSetDescription() {
        assertNull(project.getDescription());

        project.setDescription("desc");

        assertEquals("desc", project.getDescription());
    }

    @Test
    void testGeAndSetLanguage() {
        assertNull(project.getMain_language());

        project.setMain_language(LANGUAGE);

        assertEquals(LANGUAGE, project.getMain_language());
    }

    @Test
    void testGeAndSetReadme() {
        assertNull(project.getReadme());

        project.setReadme(README);

        assertEquals(README, project.getReadme());
    }

    @Test
    void testGeAndSetLink_gh() {
        assertNull(project.getLink_gh());

        project.setLink_gh("gh");

        assertEquals("gh", project.getLink_gh());
    }

    @Test
    void testGeAndSetLink_pg() {
        assertNull(project.getLink_pg());

        project.setLink_pg("pg");

        assertEquals("pg", project.getLink_pg());
    }

    @Test
    void testGeAndSetTools() {
        assertNull(project.getTools());

        project.setTools(TOOLS);

        assertEquals(TOOLS, project.getTools());
    }

    @Test
    void testGeAndSetLikes() {
        assertEquals(0, project.getLikes());

        project.setLikes(10);

        assertEquals(10, project.getLikes());
    }

    @Test
    void testConstructorFromDTO() {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(1L);
        dto.setTitle("title");
        dto.setImage("image");
        dto.setDescription("desc");
        dto.setReadme(README);
        dto.setLink_gh("gh");
        dto.setLink_pg("pg");
        dto.setTools(TOOLS);
        dto.setLikes(10);

        project = new Project(dto, LANGUAGE);

        assertAll(
                () -> assertEquals(1L, project.getId()),
                () -> assertEquals("title", project.getTitle()),
                () -> assertEquals("image", project.getImage()),
                () -> assertEquals("desc", project.getDescription()),
                () -> assertEquals(LANGUAGE, project.getMain_language()),
                () -> assertEquals(README, project.getReadme()),
                () -> assertEquals("gh", project.getLink_gh()),
                () -> assertEquals("pg", project.getLink_pg()),
                () -> assertEquals(TOOLS, project.getTools()),
                () -> assertEquals(10, project.getLikes())
        );
    }

    @Test
    void testLikeAndDislikeProject() {
        project.dislikeProject();

        assertEquals(0, project.getLikes());

        project.likeProject();

        assertEquals(1, project.getLikes());

        project.dislikeProject();

        assertEquals(0, project.getLikes());
    }
}
