package br.com.francaguilherme.myportfolio.helpers.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InvalidLoginExceptionTest {
    private final InvalidLoginException EXCEPTION = new InvalidLoginException();

    @Test
    void testExceptionMessage() {
        assertEquals("Credenciais de login inválidas.", EXCEPTION.getMessage());
    }
}
