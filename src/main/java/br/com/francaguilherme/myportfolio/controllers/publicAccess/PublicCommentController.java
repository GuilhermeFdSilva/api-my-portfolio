package br.com.francaguilherme.myportfolio.controllers.publicAccess;

import br.com.francaguilherme.myportfolio.models.DTOs.CommentDTO;
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
    public ResponseEntity<List<CommentDTO>> listComments() {
        List<CommentDTO> comments = service.listComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<CommentDTO>> listCommentsByProject(@PathVariable Long projectId) {
        List<CommentDTO> comments = service.listCommentsByProject(projectId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> saveComment(@Valid @RequestBody CommentDTO comment) {
        CommentDTO newComment = service.saveComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO comment) {
        CommentDTO commentUpToDate = service.updateComment(comment);
        return new ResponseEntity<>(commentUpToDate, HttpStatus.OK);
    }

    @PutMapping("/{voteType}")
    public ResponseEntity<CommentDTO> voteComment(@PathVariable String voteType, @Valid @RequestBody CommentDTO comment) {
        CommentDTO commentVoted = service.voteComment(comment, voteType);
        return new ResponseEntity<>(commentVoted, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
