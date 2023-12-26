package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para operações relacionadas a entidade {@link Comment}.
 * Este serviço fornece métodos para:
 *  - Listar os comentários;
 *  - Listar os comentários por projeto {@link Project};
 *  - Salvar um comentário;
 *  - Atualizar um comentário;
 *  - Deletar comentário.
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    /**
     * Retorna a lista de todos os comentários armazenados no sistema.
     *
     * @return Lista de todos os comentários.
     */
    public List<Comment> listComments() {
        return repository.findAll();
    }

    /**
     * Lista todos os comentários de um {@link Project}.
     *
     * @param id ID do projeto buscado.
     * @return A lista dos comentários do projeto.
     */
    public List<Comment> listCommentsByProject(Long id) {
        List<Comment> comments = repository.findAll();

        comments = comments
                .stream()
                .filter(comment -> comment.getProject().getId().equals(id))
                .toList();

        return comments;
    }

    /**
     * Salva um novo comentário.
     *
     * @param comment Comentario a ser salvo.
     * @return O comentário salvo com seu ID.
     */
    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }

    /**
     * Atualiza um comentário do sistema.
     *
     * @param comment Comentário atualizado com ID.
     * @return O comentário atualizado.
     */
    public Comment updateComment(Comment comment) {
        Long id = comment.getId();
        if (id != null && id > 0 && repository.existsById(id)) {
            return repository.save(comment);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Deleta um comentário do sistema.
     *
     * @param id O ID do comentario a ser deletado.
     */
    public void deleteComment(Long id) {
        if (id != null && id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
