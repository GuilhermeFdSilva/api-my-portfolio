package br.com.francaguilherme.myportfolio.models.entities;

import br.com.francaguilherme.myportfolio.models.DTOs.CommentDTO;
import jakarta.persistence.*;
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
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nick;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    private Project project;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int up;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int down;

    /**
     * Construtor para inicialização do objeto a partir de um {@link CommentDTO} e um {@link Project}.
     *
     * @param dto {@link CommentDTO} contendo os dados para inicialização do objeto.
     * @param project {@link Project} projeto relacionado ao projeto.
     */
    public Comment(CommentDTO dto, Project project) {
        this.id = dto.getId();
        this.nick = dto.getNick();
        this.message = dto.getMessage();
        this.project = project;
        this.up = dto.getUp();
        this.down = dto.getDown();
    }

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
