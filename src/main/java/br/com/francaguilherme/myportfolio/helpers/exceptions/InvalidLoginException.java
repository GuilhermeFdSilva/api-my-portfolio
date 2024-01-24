package br.com.francaguilherme.myportfolio.helpers.exceptions;

/**
 * Exceção lançada quando há uma falha na validação das credenciais fornecidas pelo usuário.
 */
public class InvalidLoginException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Credenciais de login inválidas.";
    }
}
