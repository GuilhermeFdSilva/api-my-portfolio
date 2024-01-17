package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     Classe que representa uma linguagem de programação. Essa classe utiliza a anotação {@link Entity} para ser mapeada
 *     para o banco de dados em uma tabela de mesmo nome (languages).
 * </p>
 *
 * <p>
 *     Ela também utiliza as anotações {@link Getter}, {@link Setter} e {@link NoArgsConstructor} do {@link lombok} para
 *     gerar automaticamente os getters e setters de todos os atributos da classe e um construtor sem argumentos.
 * </p>
 *
 * <p>
 *     Os atributos de um linguagem são:
 *     <ul>
 *         <li>{@link #id}: Identificador único do objeto;</li>
 *         <li>{@link #name}: Nome da linguagem;</li>
 *         <li>{@link #description}: Descrição simples da linguagem;</li>
 *         <li>{@link #type}: Tipo de linguagem (PL - Programming language, FE - Front-end, DB - Database);</li>
 *         <li>{@link #icon}: Link para o ícone da linguagem;</li>
 *         <li>{@link #stick}: Link para o stick da linguagem;</li>
 *         <li>{@link #link}: Link para a documentação da linguagem;</li>
 *         <li>{@link #main}: Valor booleano que representa se é uma das linguagens principais.</li>
 *     </ul>
 * </p>
 *
 * @see Entity
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see lombok
 */
@Getter
@Setter
@NoArgsConstructor
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
     * Enum para padronização de valores inseridos no campo {@link #type} da classe {@link Language}.
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
