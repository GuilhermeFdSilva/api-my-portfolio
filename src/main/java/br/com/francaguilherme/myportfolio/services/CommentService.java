package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Serviço para manipulação e tratamento de dados da entidade {@link Comment}. Esta classe utiliza os métodos do
 * repositório {@link CommentRepository}, para ler, salvar, atualizar e deletar comentários.
 * </p>
 *
 * <p>
 * Caso ocorra algum problema, essa classe pode lançar {@link EntityNotFoundException}.
 * </p>
 *
 * <p>
 * {@link Service} é utilizado para que o Spring identifique que essa classe é um serviço, enquanto a anotação
 * {@link Autowired} é utilizada para injeção de dependência do Spring, instanciando automaticamente
 * {@link CommentRepository}.
 * </p>
 *
 * @see Service
 * @see CommentRepository
 * @see Comment
 * @see Autowired
 * @see EntityNotFoundException
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    /**
     * Retorna a lista de todos os comentários armazenados no sistema.
     *
     * @return Lista de todos os comentários.
     * @throws EmptyListException Caso não haja nenhum comentario listado.
     */
    public List<Comment> listComments() throws EmptyListException {
        List<Comment> comments = repository.findAll();

        if (comments.isEmpty()) {
            throw new EmptyListException();
        }

        return comments;
    }

    /**
     * Lista todos os comentários de um projeto({@link Project}).
     *
     * @param id ID do projeto buscado.
     * @return A lista dos comentários do projeto.
     * @throws EmptyListException Caso não haja nenhum comentario listado.
     */
    public List<Comment> listCommentsByProject(@NonNull Long id) throws EmptyListException {
        List<Comment> comments = repository.findCommentByProjectId(id);

        if (comments.isEmpty()) {
            throw new EmptyListException();
        }

        return comments;
    }

    /**
     * Salva um novo comentário no sistema.
     *
     * @param comment Comentário a ser salvo.
     * @return O comentário salvo com seu ID.
     */
    public Comment saveComment(@NonNull Comment comment) {
        return repository.save(comment);
    }

    /**
     * Atualiza um comentário do sistema.
     *
     * @param comment Comentário atualizado com ID.
     * @return O comentário atualizado.
     * @throws EntityNotFoundException Caso do comentário seja inválido.
     */
    public Comment updateComment(@NonNull Comment comment) throws EntityNotFoundException {
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
     * @param id O ID do comentário a ser deletado.
     * @throws EntityNotFoundException Caso o ID fornecido seja inválido.
     */
    public void deleteComment(@NonNull Long id) throws EntityNotFoundException {
        if (id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
