package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.services.AdminService;
import br.com.francaguilherme.myportfolio.services.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por realizar operações de escrita de comentários.
 * Fornece endpoints para:
 * - Salvar um comentário;
 * - Votar em um comentário (up ou down);
 * - Deletar um comentário.
 */
@RestController
@RequestMapping("admin/comments")
public class CommentWriteController {
    @Autowired
    private CommentService service;
    @Autowired
    private AdminService adminService;

    /**
     * Salva um novo comentário.
     *
     * @param comment Comentario a ser salvo.
     * @return O comentário salvo com seu ID.
     */
    @PostMapping
    public ResponseEntity<?> saveComment(@RequestBody Comment comment) {
        try {
            Comment newComment = service.saveComment(comment);
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Formato da requisição incorreto - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Incrementa ou decrementa os votos de um comentário.
     *
     * @param voteType pathVariable que mostra as intenções da requisição, podendo receber:
     *                 - up;
     *                 - down;
     *                 - remove-up;
     *                 - remove-down.
     * @param comment  Comentário que recebera o voto
     * @return O comentário atualizado.
     */
    @PutMapping("/{voteType}")
    public ResponseEntity<?> voteComment(
            @PathVariable String voteType,
            @RequestBody Comment comment) {
        try {
            validateVoteType(voteType);

            if ("up".equals(voteType)) {
                comment.incrementUpVote();
            } else if ("down".equals(voteType)) {
                comment.incrementDownVote();
            } else if ("remove-up".equals(voteType)) {
                comment.decrementUpVote();
            } else if ("remove-down".equals(voteType)) {
                comment.decrementDownVote();
            }

            comment = service.updateComment(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Objeto não encontrado - " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Formato da requisição incorreto - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deleta um comentário do sistema.
     *
     * @param id    O ID do comentário a ser deletado.
     * @param admin Administrador para verificação da senha.
     * @return A resposta do servidor para a requisição.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long id,
            @RequestBody Admin admin) {
        try {
            if (adminService.validatePassword(admin.getPassword())) {
                service.deleteComment(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Autorização negada pelo servidor", HttpStatus.UNAUTHORIZED);
            }
        } catch (AdminNotFoundException e) {
            return new ResponseEntity<>("Admin não encontrado - " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Verifica se o tipo de voto é válido.
     *
     * @param voteType Tipo de voto para ser verificado.
     */
    private void validateVoteType(String voteType) {
        if (!"up".equals(voteType)
                && !"down".equals(voteType)
                && !"remove-up".equals(voteType)
                && !"remove-down".equals(voteType)) {
            throw new IllegalArgumentException();
        }
    }
}
