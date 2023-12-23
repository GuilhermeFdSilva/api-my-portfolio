package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public List<Comment> listComments() {
        return repository.findAll();
    }

    public List<Comment> listCommentsByProject(Long id) {
        List<Comment> comments = repository.findAll();

        comments = comments
                .stream()
                .filter(comment -> comment.getProject().getId().equals(id))
                .toList();

        return comments;
    }

    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Long id = comment.getId();
        if (id != null && id > 0 && repository.existsById(id)) {
            return repository.save(comment);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteComment(Long id) {
        if (id != null && id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
