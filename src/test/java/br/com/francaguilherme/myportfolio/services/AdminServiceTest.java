package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidPasswordException;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private AdminRepository repository;
    @InjectMocks
    private AdminService service;

    @Test
    void testValidatePassword_valid() throws Exception {
        Admin admin = new Admin();
        admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        when(repository.findById(1L)).thenReturn(Optional.of(admin));

        assertTrue(service.validatePassword("admin123"));
    }

    @Test
    void testValidatePassword_invalid() throws Exception {
        Admin admin = new Admin();
        admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        when(repository.findById(1L)).thenReturn(Optional.of(admin));

        assertFalse(service.validatePassword("wrongPassword"));
    }

    @Test
    void testSetPassword_validOldPassword() {
        Admin admin = new Admin();
        admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        when(repository.findById(1L)).thenReturn(Optional.of(admin));
        when(repository.save(any())).thenReturn(admin);

        Admin result = service.setPassword("admin123", "newPassword");

        assertNotNull(result);
        assertNotEquals("admin123", result.getPassword());
    }

    @Test
    void testSetPassword_invalidOldPassword() {
        Admin admin = new Admin();
        admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        when(repository.findById(1L)).thenReturn(Optional.of(admin));

        assertThrows(
                InvalidPasswordException.class,
                () -> service.setPassword("wrongPassword", "newPassword"));
    }
}
