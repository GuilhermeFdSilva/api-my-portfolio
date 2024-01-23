package br.com.francaguilherme.myportfolio.controllers.adviceController;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidLoginException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFound(EntityNotFoundException exception) {
        return new ResponseEntity<>("Dados fornecidos inválidos: erro no objeto fornecido.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handlerEmptyList(EmptyListException exception) {
        return new ResponseEntity<>("Lista de comentários vazia ou nula", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<String> handlerInvalidLogin(InvalidLoginException exception) {
        return new ResponseEntity<>("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerArgumentNotValid(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuilder response = new StringBuilder();

        violations.forEach((violation) -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            response.append(field).append(": ").append(message).append("\n");
        });

        return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
    }
}
