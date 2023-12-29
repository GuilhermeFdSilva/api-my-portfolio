package br.com.francaguilherme.myportfolio.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LanguageTest {
    private Language language;

    @BeforeEach
    void setUp() {
        language = new Language();
        language.setId(1L);
        language.setName("Java");
        language.setDescription("Java is cool");
        language.setType("PL");
        language.setIcon("google.com");
        language.setStick("google.com");
        language.setLink("google.com");
        language.setMain(true);
    }

    @Test
    void testSetAndGetId() {
        assertEquals(1L, language.getId());

        language.setId(2L);

        assertEquals(2L, language.getId());
    }

    @Test
    void testSetAndGetName() {
        assertEquals("Java", language.getName());

        language.setName("Angular");

        assertEquals("Angular", language.getName());
    }

    @Test
    void testSetAndGetDescription() {
        assertEquals("Java is cool", language.getDescription());

        language.setDescription("Java is really cool");

        assertEquals("Java is really cool", language.getDescription());
    }

    @Test
    void testSetAndGetType() {
        assertEquals("PL", language.getType());

        language.setType("FE");

        assertEquals("FE", language.getType());
    }

    @Test
    void testSetAndGetIcon() {
        assertEquals("google.com", language.getIcon());

        language.setIcon("google.com/search?q=Angular");

        assertEquals("google.com/search?q=Angular", language.getIcon());
    }

    @Test
    void testSetAndGetStick() {
        assertEquals("google.com", language.getStick());

        language.setStick("google.com/search?q=Angular");

        assertEquals("google.com/search?q=Angular", language.getStick());
    }

    @Test
    void testSetAndGetLink() {
        assertEquals("google.com", language.getLink());

        language.setLink("google.com/search?q=Angular");

        assertEquals("google.com/search?q=Angular", language.getLink());
    }

    @Test
    void testSetAndIsMain() {
        assertTrue(language.isMain());

        language.setMain(false);

        assertFalse(language.isMain());
    }
}
