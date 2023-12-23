package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.services.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/comments")
public class CommentWriteController {
    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        try {
            Comment newComment = service.saveComment(comment);
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{voteType}")
    public ResponseEntity<Comment> voteComment(
            @PathVariable String voteType,
            @RequestBody Comment comment) {
        try {
            validateVoteType(voteType);

            if ("up".equals(voteType)) {
                addUpVote(comment);
            } else if ("down".equals(voteType)) {
                addDownVote(comment);
            } else if ("remove-up".equals(voteType)) {
                removeUpVote(comment);
            } else if ("remove-down".equals(voteType)) {
                removeDownVote(comment);
            }

            service.updateComment(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            service.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateVoteType(String voteType) {
        if (!"up".equals(voteType)
                && !"down".equals(voteType)
                && !"remove-up".equals(voteType)
                && !"remove-down".equals(voteType)) {
            throw new IllegalArgumentException();
        }
    }

    private void addUpVote(Comment comment) {
        comment.setUp(comment.getUp() + 1);
    }

    private void addDownVote(Comment comment) {
        comment.setDown(comment.getDown() + 1);
    }

    private void removeUpVote(Comment comment) {
        if (comment.getUp() > 0) {
            comment.setUp(comment.getUp() - 1);
        }
    }

    private void removeDownVote(Comment comment) {
        if (comment.getDown() > 0) {
            comment.setDown(comment.getDown() + 1);
        }
    }
}
