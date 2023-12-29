package br.com.francaguilherme.myportfolio.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectTest {
    private Project project;
    private Language language;
    private String[] tools = {"Java", "Angular"};
    byte[] readme = {71, 117, 105};

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setId(1L);
        project.setTitle("My portfolio");
        project.setImage("google.com");
        project.setDescription("This is my portfolio");
        project.setTools(tools);
        project.setLink_gh("github.com");
        project.setLink_pg("francaguilherme.com");
        project.setReadme(readme);
        project.setLikes(0);

        language = new Language();
        language.setId(1L);
        project.setMain_language(language);
    }

    @Test
    void testSetAndGetId() {
        assertEquals(1L, project.getId());

        project.setId(2L);

        assertEquals(2L, project.getId());
    }

    @Test
    void testSetAndGetTitle() {
        assertEquals("My portfolio", project.getTitle());

        project.setTitle("My portfolio!!");

        assertEquals("My portfolio!!", project.getTitle());
    }

    @Test
    void testSetAndGetImage() {
        assertEquals("google.com", project.getImage());

        project.setImage("https://img.freepik.com/fotos-premium/gesto-de-mao-legal-polegar-para-cima-em-um-fundo-branco_325164-17.jpg?w=996");

        assertEquals(
                "https://img.freepik.com/fotos-premium/gesto-de-mao-legal-polegar-para-cima-em-um-fundo-branco_325164-17.jpg?w=996",
                project.getImage());
    }

    @Test
    void testSetAndGetDescription() {
        assertEquals("This is my portfolio", project.getDescription());

        project.setDescription("This is my portfolio!!!");

        assertEquals("This is my portfolio!!!", project.getDescription());
    }

    @Test
    void testSetAndGetMain_language() {
        assertEquals(language, project.getMain_language());

        language = new Language();
        language.setId(2L);
        project.setMain_language(language);

        assertEquals(language, project.getMain_language());
    }

    @Test
    void testSetAndGetTools() {
        assertEquals(tools, project.getTools());

        String[] tools1 = {"Java", "Angular", "Typescript"};
        project.setTools(tools1);

        assertEquals(tools1, project.getTools());
    }

    @Test
    void testSetAndGetLink_gh() {
        assertEquals("github.com", project.getLink_gh());

        project.setLink_gh("https://github.com/GuilhermeFdSilva/MyPortfolio");

        assertEquals("https://github.com/GuilhermeFdSilva/MyPortfolio", project.getLink_gh());
    }

    @Test
    void testSetAndGetLink_pg() {
        assertEquals("francaguilherme.com", project.getLink_pg());

        project.setLink_pg("francaguilherme.com/home");

        assertEquals("francaguilherme.com/home", project.getLink_pg());
    }

    @Test
    void testSetAndGetReadme() {
        assertEquals(readme, project.getReadme());

        byte[] readme1 = {69, 118, 101};
        project.setReadme(readme1);

        assertEquals(readme1, project.getReadme());
    }

    @Test
    void testSetAndGetLikes() {
        assertEquals(0, project.getLikes());

        project.setLikes(10);

        assertEquals(10, project.getLikes());
    }

    @Test
    void testLikeProject() {
        assertEquals(0, project.getLikes());

        project.likeProject();

        assertEquals(1, project.getLikes());
    }

    @Test
    void testDislikeProject() {
        project.dislikeProject();
        assertEquals(0, project.getLikes());

        project.likeProject();
        project.dislikeProject();
        assertEquals(0, project.getLikes());
    }
}
