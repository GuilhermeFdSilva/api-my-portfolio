package br.com.francaguilherme.myportfolio.helpers.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvalidPasswordExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Senha incorreta - " + new RuntimeException().getMessage();
        assertEquals(message, new InvalidPasswordException().getMessage());
    }
}
