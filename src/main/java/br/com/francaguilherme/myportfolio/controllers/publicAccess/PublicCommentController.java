package br.com.francaguilherme.myportfolio.controllers.publicAccess;

import br.com.francaguilherme.myportfolio.models.DTOs.CommentDTO;
import br.com.francaguilherme.myportfolio.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *     Classe com endpoints públicos para manipulação da DTO {@link CommentDTO}. Essa classe classe retorna a
 *     {@link ResponseEntity} com ou sem objetos {@link CommentDTO} e o {@link HttpStatus} adequado para a resposta da
 *     requisição.
 * </p>
 *
 * <p>
 *     Essa classe utiliza as anotações {@link RestController} para mostrar ao Spring que essa classe se trata de um
 *     Controller, e {@link RequestMapping} para definir o path padrão da classe como /public/comments.
 * </p>
 *
 * <p>
 *     Métodos presentes na classe:
 *     <ul>
 *         <li>{@link #listComments()}: Lista todos os comentários do sistema;</li>
 *         <li>{@link #listCommentsByProject(Long)}: Lista os comentários de acordo com um projeto;</li>
 *         <li>{@link #saveComment(CommentDTO)}: Salva um comentário;</li>
 *         <li>{@link #updateComment(CommentDTO)}: Atualiza um comentário;</li>
 *         <li>{@link #voteComment(String, CommentDTO)}: Avalia um comentário, positivamente ou negativamente;</li>
 *         <li>{@link #deleteComment(Long)}: Deleta um comentário.</li>
 *     </ul>
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 * @see GetMapping
 * @see PostMapping
 * @see PutMapping
 * @see DeleteMapping
 */
@RestController
@RequestMapping("/public/comments")
public class PublicCommentController {
    @Autowired
    private CommentService service;

    /**
     * Lista todos os comentários salvos no sistema.
     *
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e a lista dos comentários.
     */
    @GetMapping
    public ResponseEntity<List<CommentDTO>> listComments() {
        List<CommentDTO> comments = service.listComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Lista os comentários de acordo com um projeto.
     *
     * @param projectId ID do projeto para pesquisa.
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e a lista dos comentários filtrados.
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<List<CommentDTO>> listCommentsByProject(@PathVariable Long projectId) {
        List<CommentDTO> comments = service.listCommentsByProject(projectId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Salva um novo comentário.
     *
     * @param comment DTO de comentário para ser salva.
     * @return {@link ResponseEntity} com {@link HttpStatus#CREATED} e o novo comentário criado.
     */
    @PostMapping
    public ResponseEntity<CommentDTO> saveComment(@Valid @RequestBody CommentDTO comment) {
        CommentDTO newComment = service.saveComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    /**
     * Atualiza um comentário do sistema.
     *
     * @param comment DTO do comentário atializado.
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e o comentário atualizado.
     */
    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO comment) {
        CommentDTO commentUpToDate = service.updateComment(comment);
        return new ResponseEntity<>(commentUpToDate, HttpStatus.OK);
    }

    /**
     * Avalia um comentário, positivamente ou negativamente.
     *
     * @param voteType Tipo de voto:
     *                 <ul>
     *                     <li>up</li>
     *                     <li>down</li>
     *                     <li>remove-up</li>
     *                     <li>remove-down</li>
     *                 </ul>
     * @param comment Comentário alvo do voto.
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e o comentário com o número de votos atualizado.
     */
    @PutMapping("/{voteType}")
    public ResponseEntity<CommentDTO> voteComment(@PathVariable String voteType, @Valid @RequestBody CommentDTO comment) {
        CommentDTO commentVoted = service.voteComment(comment, voteType);
        return new ResponseEntity<>(commentVoted, HttpStatus.OK);
    }

    /**
     * Deleta um comentário do sistema.
     *
     * @param id ID do comentário a ser deletado.
     * @return {@link ResponseEntity} com {@link HttpStatus#NO_CONTENT}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
