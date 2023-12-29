package br.com.francaguilherme.myportfolio.models;

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
        admin.setId(1L);
        admin.setPassword("admin123");
    }

    @Test
    void testSetAndGetId () {
        assertEquals(1L, admin.getId());

        admin.setId(2L);

        assertEquals(2L, admin.getId());
    }

    @Test
    void testSetAndGetPassword() {
        assertEquals("admin123", admin.getPassword());

        admin.setPassword("newPassword");

        assertEquals("newPassword", admin.getPassword());
    }
}
