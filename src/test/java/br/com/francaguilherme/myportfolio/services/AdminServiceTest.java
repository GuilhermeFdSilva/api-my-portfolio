package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidLoginException;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private AdminRepository repository;
    @InjectMocks
    private AdminService service;

    private final Admin ADMIN = new Admin();
    private final Admin ADMIN_FOUND = new Admin();

    @BeforeEach
    void setUp() {
        ADMIN.setLogin("login");
        ADMIN.setPassword("password");

        ADMIN_FOUND.setLogin("login");
    }

    @Test
    void testValidatePassword() {
        ADMIN_FOUND.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(repository.findAdminByLogin(ADMIN.getLogin())).thenReturn(Optional.of(ADMIN_FOUND));

        service.validatePassword(ADMIN);

        verify(repository, times(1)).findAdminByLogin(ADMIN.getLogin());
    }

    @Test
    void testValidatePasswordInvalidLogin() {
        when(repository.findAdminByLogin(ADMIN.getLogin())).thenThrow(InvalidLoginException.class);

        assertThrows(InvalidLoginException.class, () -> service.validatePassword(ADMIN));
    }

    @Test
    void testValidatePasswordInvalidPassword() {
        ADMIN_FOUND.setPassword(new BCryptPasswordEncoder().encode("otherPassword"));

        when(repository.findAdminByLogin(ADMIN.getLogin())).thenReturn(Optional.of(ADMIN_FOUND));

        assertThrows(InvalidLoginException.class, () -> service.validatePassword(ADMIN));
    }

    @Test
    void testSetCredentials() {
        Admin adminUpToDate = new Admin();
        adminUpToDate.setLogin("newLogin");
        adminUpToDate.setPassword("newPassword");

        Admin oldAdmin = new Admin();
        oldAdmin.setLogin("login");
        oldAdmin.setPassword("password");

        ADMIN.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(repository.findAdminByLogin(oldAdmin.getLogin())).thenReturn(Optional.of(ADMIN));

        service.setCredentials(oldAdmin, adminUpToDate);

        assertEquals(adminUpToDate.getLogin(), ADMIN.getLogin());
        assertTrue(new BCryptPasswordEncoder().matches(adminUpToDate.getPassword(), ADMIN.getPassword()));

        verify(repository, times(1)).save(ADMIN);
    }
}
