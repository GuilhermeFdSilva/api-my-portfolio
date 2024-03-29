package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.models.DTOs.CommentDTO;
import br.com.francaguilherme.myportfolio.models.entities.Comment;
import br.com.francaguilherme.myportfolio.models.entities.Project;
import br.com.francaguilherme.myportfolio.repositories.CommentRepository;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     Serviço para manipulação e tratamento de dados da entidade {@link Comment} atraves da DTO {@link CommentDTO}. Esta
 *     classe utiliza os métodos do repositório {@link CommentRepository}, para ler, salvar, atualizar e deletar comentários.
 * </p>
 *
 * <p>
 *     Caso ocorra algum problema, essa classe pode lançar {@link EntityNotFoundException} ou {@link EmptyListException}.
 * </p>
 *
 * <p>
 *     {@link Service} é utilizado para que o Spring identifique que essa classe é um serviço, enquanto a anotação
 *     {@link Autowired} é utilizada para injeção de dependência do Spring, instanciando automaticamente
 *     {@link CommentRepository}.
 * </p>
 *
 * @see Service
 * @see CommentRepository
 * @see Comment
 * @see CommentDTO
 * @see Autowired
 * @see EntityNotFoundException
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Retorna a lista de todos os comentários armazenados no sistema.
     *
     * @return Lista de todos os comentários.
     * @throws EmptyListException Caso não haja nenhum comentario listado.
     */
    public List<CommentDTO> listComments() throws EmptyListException {
        List<CommentDTO> comments = CommentDTO.listToDTO(repository.findAll());

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
    public List<CommentDTO> listCommentsByProject(@NonNull Long id) throws EmptyListException {
        List<CommentDTO> comments = CommentDTO.listToDTO(repository.findByProjectId(id));

        if (comments.isEmpty()) {
            throw new EmptyListException();
        }

        return comments;
    }

    /**
     * Salva um novo comentário no sistema.
     *
     * @param dto {@link CommentDTO} representando o comentário a ser salvo.
     * @return O comentário salvo com seu ID.
     */
    public CommentDTO saveComment(@NonNull CommentDTO dto) {
        Project project = projectRepository.findById(dto.getProject_id()).orElseThrow(EntityNotFoundException::new);

        Comment comment = new Comment(dto, project);

        return CommentDTO.toDTO(repository.save(comment));
    }

    /**
     * Atualiza um comentário do sistema.
     *
     * @param dto {@link CommentDTO} do comentário atualizado com ID.
     * @return O comentário atualizado.
     * @throws EntityNotFoundException Caso do comentário seja inválido.
     */
    public CommentDTO updateComment(@NonNull CommentDTO dto) throws EntityNotFoundException {
        Long id = dto.getId();

        if (id != null && id > 0 && repository.existsById(id)) {
            Project project = projectRepository.findById(dto.getProject_id()).orElseThrow(EntityNotFoundException::new);

            Comment comment = new Comment(dto, project);

            return CommentDTO.toDTO(repository.save(comment));
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Vota em um comentário de acordo com o tipo de voto(voteType).
     *
     * @param dto {@link CommentDTO} do comentário alvo.
     * @param voteType Tipo de voto:
     *                 <ul>
     *                     <li>up</li>
     *                     <li>down</li>
     *                     <li>remove-up</li>
     *                     <li>remove-down</li>
     *                 </ul>
     * @return O comentário com o número de votos atualizado.
     * @throws EntityNotFoundException Caso do comentário seja inválido.
     */
    public CommentDTO voteComment(@NonNull CommentDTO dto, @NonNull String voteType) throws EntityNotFoundException {
        Comment commentOnDB = repository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);

        // De acordo com o voteType fornecido, sera chamado o metodo adequado.
        switch (voteType) {
            case "up" -> commentOnDB.incrementUpVote();
            case "down" -> commentOnDB.incrementDownVote();
            case "remove-up" -> commentOnDB.decrementUpVote();
            case "remove-down" -> commentOnDB.decrementDownVote();
        }

        return CommentDTO.toDTO(repository.save(commentOnDB));
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
