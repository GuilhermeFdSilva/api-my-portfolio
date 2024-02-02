package br.com.francaguilherme.myportfolio.models.DTOs;

import br.com.francaguilherme.myportfolio.models.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * Objeto de transferência de dados (DTO) de {@link Comment}. Esta classe é utilizada para manipulação e exibição de
 * dados relacionados a entidade {@link Comment}.
 * </p>
 *
 * <p>
 * Ela utiliza as anotações {@link Getter}, {@link Setter} e {@link NoArgsConstructor} do {@link lombok} para
 * gerar automaticamente os getters e setters de todos os atributos da classe e um construtor sem argumentos.
 * </p>
 *
 * <p>
 * Os atributos que essa classe utiliza são:
 *     <ul>
 *         <li>{@link #id}: Identificador do Comentário;</li>
 *         <li>{@link #nick}: Apelido de quem fez o comentário;</li>
 *         <li>{@link #message}: Mensagem do comentário;</li>
 *         <li>{@link #up}: Votos positivos do comentário;</li>
 *         <li>{@link #down}: Votos negativos do comentário;</li>
 *         <li>{@link #project_id}: ID do projeto associado ao comentário;</li>
 *     </ul>
 * </p>
 *
 * @see Comment
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see lombok
 */
@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String nick;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String message;

    @NotNull(message = "Não pode ser nulo")
    private Long project_id;

    private int up;

    private int down;

    /**
     * Construtor para inicialização de um objeto a partir de um {@link Comment}.
     *
     * @param comment {@link Comment} contendo os dados para inicialização.
     */
    public CommentDTO(@NonNull Comment comment) {
        this.id = comment.getId();
        this.nick = comment.getNick();
        this.message = comment.getMessage();
        this.project_id = comment.getProject().getId();
        this.up = comment.getUp();
        this.down = comment.getDown();
    }

    /**
     * Função estática para conversão de um objeto {@link Comment} em um objeto {@link CommentDTO}.
     *
     * @param comment {@link Comment} a ser convertido.
     * @return Objeto convertido para {@link CommentDTO}.
     */
    public static CommentDTO toDTO(Comment comment) {
        return new CommentDTO(comment);
    }

    /**
     * Função estática para conversão de uma lista de {@link Comment} em uma lista de {@link CommentDTO}.
     *
     * @param comments Lista de {@link Comment} para conversão.
     * @return Lista com objetos convertidos para {@link CommentDTO}.
     */
    public static List<CommentDTO> listToDTO(List<Comment> comments) {
        return comments.stream().map(CommentDTO::new).toList();
    }
}
