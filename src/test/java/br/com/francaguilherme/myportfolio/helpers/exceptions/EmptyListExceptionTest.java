package br.com.francaguilherme.myportfolio.helpers.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmptyListExceptionTest {
    private final EmptyListException EXCEPTION = new EmptyListException();

    @Test
    void testExceptionMessage() {
        assertEquals("Lista de objetos vazia ou nulla", EXCEPTION.getMessage());
    }
}
