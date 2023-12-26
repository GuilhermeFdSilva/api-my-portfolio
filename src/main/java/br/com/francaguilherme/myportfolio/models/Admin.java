package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;

/**
 * Representa o administrador do sistema.
 * Esta classe armazena informações sobre o administrador, incluindo a senha (password).
 */
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    /**
     * Obtém o ID do administrador.
     * @return O ID do administrador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do administrador.
     * @param id O novo ID do administrador.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém a senha do administrador.
     * @return A senha do administrador.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do administrador.
     * @param password A nova senha do administrador.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
