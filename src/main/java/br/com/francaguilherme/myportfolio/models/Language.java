package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;

/**
 * Representa uma linguagem de programação.
 * Esta classe armazena informações da linguagem de programação, incluindo:
 *  - Nome da linguagem (name);
 *  - Descrição da linguagem (description)
 *  - Tipo de linguagem (type: PL, FE, DB);
 *      - PL: Programming language (refere-se a linguagens de programação);
 *      - FE: Front-End (refere-se a ferramentas de programação para Front-End);
 *      - DB: Database (refere-se a sistemas de Gerenciamento de Banco de Dados (SGBDs)).
 *  - Link para o ícone da linguagem (icon);
 *  - Link para o stick da linguagem (stick);
 *  - Link para a documentação da linguagem (link);
 *  - Se a linguagem de programação é uma das principais (main).
 */
@Entity(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, insertable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    @Column(nullable = false, length = 2)
    private String type;
    private String icon;
    @Column(nullable = false)
    private String stick;
    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private boolean main;

    /**
     * Obtém o ID da linguagem.
     * @return O ID da linguagem.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da linguagem.
     * @param id O novo ID da linguagem.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da linguagem.
     * @return O nome da linguagem.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da linguagem.
     * @param name O novo nome da linguagem.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a descrição da linguagem.
     * @return A descrição da linguagem.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição da linguagem.
     * @param description A nova descrição da linguagem.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtém o tipo da linguagem.
     * @return O tipo da linguagem.
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo da linguagem.
     * @param type O novo tipo da linguagem.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Obtem o link para o ícone da linguagem.
     * @return O link para o ícone da linguagem.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Define o link para o ícone da linguagem.
     * @param icon O novo link para o ícone da linguagem.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Obtém o link para o stick da linguagem.
     * @return O link para o stick da linguagem.
     */
    public String getStick() {
        return stick;
    }

    /**
     * Define o link para o stick da linguagem.
     * @param stick O novo link para o stick da linguagem.
     */
    public void setStick(String stick) {
        this.stick = stick;
    }

    /**
     * Obtém o link para a documentação da linguagem.
     * @return O link para a documentação da linguagem.
     */
    public String getLink() {
        return link;
    }

    /**
     * Define o link para a documentação da linguagem.
     * @param link Novo link para a documentação da linguagem.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Obtém se esta é uma linguagem principal.
     * @return {@code true} se é uma linguagem principal, {@code false}, caso contrário.
     */
    public boolean isMain() {
        return main;
    }

    /**
     * Define se esta é uma linguagem principal.
     * @param main Nova definição de principal.
     */
    public void setMain(boolean main) {
        this.main = main;
    }
}
