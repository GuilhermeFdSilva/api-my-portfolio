package br.com.francaguilherme.myportfolio.helpers.exceptions;

/**
 * Exceção lançada quando uma lista de dados do banco está vazia ou nula.
 */
public class EmptyListException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Lista de objetos vazia ou nulla";
    }
}
