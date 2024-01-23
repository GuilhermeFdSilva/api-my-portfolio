package br.com.francaguilherme.myportfolio.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    private LanguageType type;

    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String stick;

    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String link;

    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    private boolean main;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    private String icon;

    /**
     * Enum para padronização de valores inseridos no campo {@link #type} da classe {@link Language}.
     */
    public enum LanguageType {
        PL,
        FE,
        DB
    }
}
