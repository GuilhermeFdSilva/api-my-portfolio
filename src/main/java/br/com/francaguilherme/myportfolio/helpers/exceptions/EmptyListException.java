package br.com.francaguilherme.myportfolio.helpers.exceptions;

public class EmptyListException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Lista de objetos vazia ou nulla";
    }
}
