package br.com.francaguilherme.myportfolio.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminTest {
    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin();
    }

    @Test
    void testSetAndGetId() {
        assertNull(admin.getId());

        admin.setId(1L);

        assertEquals(1L, admin.getId());
    }

    @Test
    void testSetAndGetLogin() {
        assertNull(admin.getLogin());

        admin.setLogin("login");

        assertEquals("login", admin.getLogin());
    }

    @Test
    void testSetAndGetPassword() {
        assertNull(admin.getPassword());

        admin.setPassword("password");

        assertEquals("password", admin.getPassword());
    }
}
