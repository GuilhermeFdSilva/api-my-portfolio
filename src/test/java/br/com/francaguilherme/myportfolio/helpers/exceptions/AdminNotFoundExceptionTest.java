package br.com.francaguilherme.myportfolio.helpers.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Usuário administrador não encontrado - " + new RuntimeException().getMessage();
        assertEquals(message, new AdminNotFoundException().getMessage());
    }
}
