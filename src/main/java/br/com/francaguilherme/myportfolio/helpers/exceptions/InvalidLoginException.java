package br.com.francaguilherme.myportfolio.helpers.exceptions;

/**
 * Exceção lançada quando uma senha é considerada inválida.
 */
public class InvalidLoginException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Credenciais de login inválidas.";
    }
}
