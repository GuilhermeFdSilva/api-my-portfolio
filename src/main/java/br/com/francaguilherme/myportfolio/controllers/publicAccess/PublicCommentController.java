package br.com.francaguilherme.myportfolio.controllers.publicAccess;

import br.com.francaguilherme.myportfolio.models.entities.Comment;
import br.com.francaguilherme.myportfolio.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/comments")
public class PublicCommentController {
    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<List<Comment>> listComments() {
        List<Comment> comments = service.listComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<Comment>> listCommentsByProject(@PathVariable Long projectId) {
        List<Comment> comments = service.listCommentsByProject(projectId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody Comment comment) {
        Comment newComment = service.saveComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment comment) {
        Comment commentUpToDate = service.updateComment(comment);
        return new ResponseEntity<>(commentUpToDate, HttpStatus.OK);
    }

    @PutMapping("/{voteType}")
    public ResponseEntity<Comment> voteComment(@PathVariable String voteType, @Valid @RequestBody Comment comment) {
        Comment commentVoted = service.voteComment(comment, voteType);
        return new ResponseEntity<>(commentVoted, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
