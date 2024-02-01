package br.com.francaguilherme.myportfolio.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LanguageTest {
    private Language language;
    private final Language.LanguageType TYPE = Language.LanguageType.PL;

    @BeforeEach
    void setUp () {
        language = new Language();
    }

    @Test
    void testGetAndSetId() {
        assertNull(language.getId());

        language.setId(1L);

        assertEquals(1L, language.getId());
    }

    @Test
    void testGetAndSetName() {
        assertNull(language.getName());

        language.setName("name");

        assertEquals("name", language.getName());
    }

    @Test
    void testGetAndSetType() {
        assertNull(language.getType());

        language.setType(TYPE);

        assertEquals(TYPE, language.getType());
    }

    @Test
    void testGetAndSetStick() {
        assertNull(language.getStick());

        language.setStick("stick");

        assertEquals("stick", language.getStick());
    }

    @Test
    void testGetAndSetLink() {
        assertNull(language.getLink());

        language.setLink("link");

        assertEquals("link", language.getLink());
    }

    @Test
    void testGetAndSetMain() {
        assertFalse(language.isMain());

        language.setMain(true);

        assertTrue(language.isMain());
    }

    @Test
    void testGetAndSetDescription() {
        assertNull(language.getDescription());

        language.setDescription("desc");

        assertEquals("desc", language.getDescription());
    }

    @Test
    void testGetAndSetIcon() {
        assertNull(language.getIcon());

        language.setIcon("icon");

        assertEquals("icon", language.getIcon());
    }
}
