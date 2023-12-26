package br.com.francaguilherme.myportfolio.controllers.read;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador responsável por realizar operações de leitura de comentários.
 * Fornece endpoints para:
 *  - Listar todos os comentários no sistema;
 *  - Listar comentários por {@link Project}.
 */
@RestController
@RequestMapping("/comments")
public class CommentReadController {
    @Autowired
    private CommentService service;

    /**
     * Retorna a lista de todos os comentários armazenados no sistema.
     *
     * @return Lista de todos os comentários.
     */
    @GetMapping
    public ResponseEntity<List<Comment>> listComments() {
        List<Comment> comments = service.listComments();

        return (comments != null && !comments.isEmpty())
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Lista todos os comentários de um {@link Project}
     *
     * @param projectId ID do projeto buscado.
     * @return A lista dos comentários do projeto.
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<List<Comment>> listCommentsByProject(@PathVariable Long projectId) {
        List<Comment> comments = service.listCommentsByProject(projectId);

        return (comments != null && !comments.isEmpty())
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
