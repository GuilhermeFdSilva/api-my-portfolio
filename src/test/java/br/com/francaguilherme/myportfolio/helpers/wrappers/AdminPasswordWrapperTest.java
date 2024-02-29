package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.entities.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminPasswordWrapperTest {
    private Admin oldAdmin;
    private Admin newAdmin;
    private AdminPasswordWrapper wrapper = new AdminPasswordWrapper();

    @BeforeEach
    void setUp() {
        oldAdmin = new Admin();
        newAdmin = new Admin();

        wrapper = new AdminPasswordWrapper();
        wrapper.setOldAdmin(oldAdmin);
        wrapper.setNewAdmin(newAdmin);
    }

    @Test
    void testSetAndGetOldAdmin() {
        assertEquals(oldAdmin, wrapper.getOldAdmin());

        oldAdmin = new Admin();
        wrapper.setOldAdmin(oldAdmin);

        assertEquals(oldAdmin, wrapper.getOldAdmin());
    }

    @Test
    void testSetAndGetNewAdmin() {
        assertEquals(newAdmin, wrapper.getNewAdmin());

        newAdmin = new Admin();
        wrapper.setNewAdmin(newAdmin);

        assertEquals(newAdmin, wrapper.getNewAdmin());
    }
}
