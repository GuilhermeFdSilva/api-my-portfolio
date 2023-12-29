package br.com.francaguilherme.myportfolio.helpers.exceptions;

public class AdminNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Usuário administrador não encontrado - " + super.getMessage();
    }
}
