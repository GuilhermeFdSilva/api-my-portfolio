package br.com.francaguilherme.myportfolio.controllers.read;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentReadController {
    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<List<Comment>> listComments() {
        List<Comment> comments = service.listComments();

        return (comments != null && !comments.isEmpty())
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<Comment>> listCommentsByProject(@PathVariable Long projectId) {
        List<Comment> comments = service.listCommentsByProject(projectId);

        return (comments != null && !comments.isEmpty())
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
