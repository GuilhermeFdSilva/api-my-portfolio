package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Classe que representa um comentário feito em um projeto ({@link Project}). Esta classe utiliza a anotação
 * {@link Entity} para ser mapeada para o banco de dados em uma tabela de mesmo nome (comments).
 * </p>
 *
 * <p>
 * Ela também utiliza as anotações {@link Getter}, {@link Setter} e {@link NoArgsConstructor} do {@link lombok} para
 * gerar automaticamente os getters e setters de todos os atributos da classe e um construtor sem argumentos.
 * </p>
 *
 * <p>
 * Os atributos de um comentário são:
 *     <ul>
 *         <li>{@link #id}: Identificador único do objeto;</li>
 *         <li>{@link #nick}: Apelido de quem fez o comentário;</li>
 *         <li>{@link #message}: Mensagem escrita no comentário;</li>
 *         <li>{@link #up}: Votos positivos no comentário;</li>
 *         <li>{@link #down}: Votos negativos no comentário;</li>
 *         <li>{@link #project}: Projeto comentado ({@link Project}).</li>
 *     </ul>
 * </p>
 *
 * @see Entity
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see lombok
 * @see Project
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(length = 30, nullable = false)
    @NotNull(message = "O campo 'nick' não pode ser nulo")
    @NotBlank(message = "O campo 'nick' não pode estar em branco")
    private String nick;

    @Column(nullable = false)
    @NotNull(message = "O campo 'message' não pode ser nulo")
    @NotBlank(message = "O campo 'message' não estar em branco")
    private String message;

    @ManyToOne
    @NotNull(message = "O campo 'project' não pode ser nulo")
    private Project project;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int up;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int down;

    /**
     * Adiciona 1 ao número de votos positivos({@link #up}).
     */
    public void incrementUpVote() {
        up++;
    }

    /**
     * Remove 1 do número de votos positivos({@link #up}), caso esse número seja maior que 0
     */
    public void decrementUpVote() {
        if (up > 0) {
            up--;
        }
    }

    /**
     * Adiciona 1 ao número de votos negativos({@link #down}).
     */
    public void incrementDownVote() {
        down++;
    }

    /**
     * Remove 1 do número de votos negativos({@link #down}), caso esse número seja maior que 0.
     */
    public void decrementDownVote() {
        if (down > 0) {
            down--;
        }
    }
}
