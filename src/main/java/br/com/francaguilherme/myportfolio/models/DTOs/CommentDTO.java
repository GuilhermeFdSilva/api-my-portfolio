package br.com.francaguilherme.myportfolio.models.DTOs;

import br.com.francaguilherme.myportfolio.models.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

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

    public CommentDTO(@NonNull Comment comment) {
        this.id = comment.getId();
        this.nick = comment.getNick();
        this.message = comment.getMessage();
        this.project_id = comment.getProject().getId();
        this.up = comment.getUp();
        this.down = comment.getDown();
    }

    public static List<CommentDTO> listToDTO(List<Comment> comments) {
        return comments.stream().map(CommentDTO::new).toList();
    }

    public static CommentDTO toDTO (Comment comment) {
        return new CommentDTO(comment);
    }
}
