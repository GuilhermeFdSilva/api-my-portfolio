package br.com.francaguilherme.myportfolio.helpers.exceptions;

/**
 * Exceção lançada quando uma senha é considerada inválida.
 */
public class InvalidPasswordException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Senha incorreta - " + super.getMessage();
    }
}
