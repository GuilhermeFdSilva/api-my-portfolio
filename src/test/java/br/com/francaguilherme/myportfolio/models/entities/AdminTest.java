package br.com.francaguilherme.myportfolio.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdminTest {
    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        admin.setId(1L);
        admin.setLogin("admin");
        admin.setPassword("admin123");
    }

    @Test
    void testSetAndGetId() {
        assertEquals(1L, admin.getId());

        admin.setId(2L);

        assertEquals(2L, admin.getId());
    }

    @Test
    void testSetAndGetLogin() {
        assertEquals("admin", admin.getLogin());

        admin.setLogin("admin2");

        assertEquals("admin2", admin.getLogin());
    }

    @Test
    void testSetAndGetPassword() {
        assertEquals("admin123", admin.getPassword());

        admin.setPassword("newPassword");

        assertEquals("newPassword", admin.getPassword());
    }
}
