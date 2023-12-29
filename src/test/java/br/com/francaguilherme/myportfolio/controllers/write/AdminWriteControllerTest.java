package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidPasswordException;
import br.com.francaguilherme.myportfolio.helpers.wrappers.AdminPasswordWrapper;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.services.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdminWriteControllerTest {
    @Mock
    private AdminService service;

    @InjectMocks
    private AdminWriteController controller;

    @Test
    void testSetPassword_valid() {
        AdminPasswordWrapper wrapper = new AdminPasswordWrapper();
        Admin oldAdmin = new Admin();
        Admin newAdmin = new Admin();
        oldAdmin.setPassword("admin123");
        newAdmin.setPassword("newPassword");
        wrapper.setOldPassword(oldAdmin);
        wrapper.setNewPassword(newAdmin);

        when(service.setPassword(any(), any())).thenReturn(newAdmin);

        ResponseEntity<?> response = controller.setPassword(wrapper);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newAdmin, response.getBody());
        verify(service, times(1)).setPassword(oldAdmin.getPassword(), newAdmin.getPassword());
    }

    @Test
    void testSetPassword_InvalidPassword() {
        AdminPasswordWrapper wrapper = new AdminPasswordWrapper();
        Admin oldAdmin = new Admin();
        Admin newAdmin = new Admin();
        oldAdmin.setPassword("admin123");
        newAdmin.setPassword("newPassword");
        wrapper.setOldPassword(oldAdmin);
        wrapper.setNewPassword(newAdmin);

        when(service.setPassword(any(), any())).thenThrow(new InvalidPasswordException());

        ResponseEntity<?> response = controller.setPassword(wrapper);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor" + new InvalidPasswordException().getMessage(), response.getBody());

        verify(service, times(1)).setPassword(oldAdmin.getPassword(), newAdmin.getPassword());
    }

    @Test
    void testSetPassword_AdminNotFound() {
        AdminPasswordWrapper wrapper = new AdminPasswordWrapper();
        Admin oldAdmin = new Admin();
        Admin newAdmin = new Admin();
        oldAdmin.setPassword("admin123");
        newAdmin.setPassword("newPassword");
        wrapper.setOldPassword(oldAdmin);
        wrapper.setNewPassword(newAdmin);

        when(service.setPassword(any(), any())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.setPassword(wrapper);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new AdminNotFoundException().getMessage(), response.getBody());

        verify(service, times(1)).setPassword(oldAdmin.getPassword(), newAdmin.getPassword());
    }
}
