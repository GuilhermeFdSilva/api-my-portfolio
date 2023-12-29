package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminPasswordWrapperTest {
    private Admin oldPassword;
    private Admin newPassword;
    private AdminPasswordWrapper wrapper;

    @BeforeEach
    void setUp() {
        oldPassword = new Admin();
        oldPassword.setPassword("admin123");

        newPassword = new Admin();
        newPassword.setPassword("newPassword");

        wrapper = new AdminPasswordWrapper();
        wrapper.setOldPassword(oldPassword);
        wrapper.setNewPassword(newPassword);
    }

    @Test
    void testSetAndGetOldPassword() {
        assertEquals(oldPassword.getPassword(), wrapper.getOldPassword());

        oldPassword = new Admin();
        oldPassword.setPassword("admin123456");
        wrapper.setOldPassword(oldPassword);

        assertEquals(oldPassword.getPassword(), wrapper.getOldPassword());
    }

    @Test
    void testSetAndGetNewPassword() {
        assertEquals(newPassword.getPassword(), wrapper.getNewPassword());

        newPassword = new Admin();
        newPassword.setPassword("newNewPassword");
        wrapper.setNewPassword(newPassword);

        assertEquals(newPassword.getPassword(), wrapper.getNewPassword());
    }
}
