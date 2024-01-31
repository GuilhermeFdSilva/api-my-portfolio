package br.com.francaguilherme.myportfolio.controllers.adviceController;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidLoginException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *     Classe para controle de exceções ocorridas durante as requisições. Essa classe retorna a {@link ResponseEntity}
 *     com o {@link HttpStatus} adequado de acordo com a exceção lançada.
 * </p>
 *
 * <p>
 *     Essa classe utiliza a anotação {@link ControllerAdvice} para indicar ao Spring que esta classe é responsável pelo
 *     tratamento de exceções. Também é utilizado nos métodos a anotação {@link ExceptionHandler} com a referência da
 *     exceção que será tratada pelo método.
 * </p>
 *
 * @see ControllerAdvice
 * @see ExceptionHandler
 * @see ResponseEntity
 * @see HttpStatus
 */
@ControllerAdvice
public class ExceptionHandlers {

    /**
     * Função para tratamento da {@link EntityNotFoundException}.
     *
     * @param exception Exceção emitida ao fazer a requisição.
     * @return {@link ResponseEntity} com a mensagem de erro adequada.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFound(EntityNotFoundException exception) {
        return new ResponseEntity<>("Dados fornecidos inválidos: erro no objeto fornecido.", HttpStatus.NOT_FOUND);
    }

    /**
     * Função para tratamento da {@link EmptyListException}.
     *
     * @param exception Exceção emitida ao fazer a requisição.
     * @return {@link ResponseEntity} com a mensagem de erro adequada.
     */
    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handlerEmptyList(EmptyListException exception) {
        return new ResponseEntity<>("Lista de comentários vazia ou nula", HttpStatus.NO_CONTENT);
    }

    /**
     * Função para tratamento da {@link InvalidLoginException}.
     *
     * @param exception Exceção emitida ao fazer a requisição.
     * @return {@link ResponseEntity} com a mensagem de erro adequada.
     */
    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<String> handlerInvalidLogin(InvalidLoginException exception) {
        return new ResponseEntity<>("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Função para tratamento da {@link ConstraintViolationException}.
     *
     * @param exception Exceção emitida ao fazer a requisição.
     * @return {@link ResponseEntity} com a mensagem de erro adequada.
     */
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

    /**
     * Função para tratamento da {@link MethodArgumentNotValidException}.
     *
     * @param exception Exceção emitida ao fazer a requisição.
     * @return {@link ResponseEntity} com a mensagem de erro adequada.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder("Campos necessarios: \n");

        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(";\n");
        }

        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }
}
