package br.com.francaguilherme.myportfolio.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     Classe que representa um projeto. Esta classe utiliza a anotação {@link Entity} para ser mapeada para o banco de
 *     dados em uma tabela de mesmo nome (projects).
 * </p>
 *
 * <p>
 *     Ela também utiliza as anotações {@link Getter}, {@link Setter} e {@link NoArgsConstructor} do {@link lombok} para
 *     gerar automaticamente os getters e setters de todos os atributos da classe e um construtor sem argumentos.
 * </p>
 *
 * <p>
 *     Os atributos de um projeto são:
 *     <ul>
 *         <li>{@link #id}: Identificador único do objeto;</li>
 *         <li>{@link #title}: Representa o título/ nome do projeto;</li>
 *         <li>{@link #image}: Link para a imagem do projeto;</li>
 *         <li>{@link #description}: Descrição simples do projeto;</li>
 *         <li>{@link #main_language}: Principal linguagem utilizada no projeto ({@link Language});</li>
 *         <li>{@link #tools}: Lista de nomes de todas as ferramentas utilizadas no projeto;</li>
 *         <li>{@link #link_gh}: Link para o GitHub do projeto;</li>
 *         <li>{@link #link_pg}: Link para página do projeto, caso tenha;</li>
 *         <li>{@link #readme}: README.md do projeto;</li>
 *         <li>{@link #likes}: Quantidade de likes do projeto.</li>
 *     </ul>
 * </p>
 *
 * @see Entity
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see lombok
 * @see Language
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String image;

    @Column(columnDefinition = "MEDIUMTEXT")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String description;

    @ManyToOne
    @NotNull(message = "Não pode ser nulo")
    private Language main_language;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @NotNull(message = "Não pode ser nulo")
    private byte[] readme;

    @Column(nullable = false)
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String link_gh;

    private String link_pg;

    private String[] tools;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int likes;

    /**
     * Adiciona 1 ao número de {@link #likes} do projeto.
     */
    public void likeProject() {
        likes++;
    }

    /**
     * Remove 1 do número de {@link #likes} do projeto caso esse número seja maior que 0.
     */
    public void dislikeProject() {
        if (likes > 0) {
            likes--;
        }
    }
}
