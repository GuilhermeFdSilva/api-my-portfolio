package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository repository;
    @InjectMocks
    private CommentService service;

    @Test
    void testListComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        when(repository.findAll()).thenReturn(comments);

        List<Comment> result = service.listComments();

        assertEquals(comments, result);
    }

    @Test
    void testListCommentsByProject() {
        List<Comment> comments = new ArrayList<>();
        for (long i = 0; i < 3; i++) {
            Comment comment = new Comment();
            Project project = new Project();

            project.setId(i);
            comment.setProject(project);

            comments.add(comment);
        }

        when(repository.findAll()).thenReturn(comments);

        List<Comment> result = service.listCommentsByProject(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getProject().getId());
    }

    @Test
    void testSaveComment() {
        Comment comment = new Comment();

        when(repository.save(any())).thenReturn(comment);

        Comment result = service.saveComment(comment);

        assertEquals(comment, result);
    }

    @Test
    void testUpdateComment_validComment() {
        Comment comment = new Comment();
        comment.setId(1L);

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any())).thenReturn(comment);

        Comment result = service.updateComment(comment);

        assertEquals(comment, result);
    }

    @Test
    void testUpdateComment_invalidComment() {
        assertAll(
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateComment(createCommentWhitId(null))),
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateComment(createCommentWhitId(0L))),
                () -> assertThrows(EntityNotFoundException.class, () -> service.updateComment(createCommentWhitId(1L)))
        );
    }

    @Test
    void testDeleteComment_validComment() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteComment(1L);

        verify(repository).deleteById(anyLong());
    }

    @Test
    void testDeleteComment_invalidComment() {
        when(repository.existsById(anyLong())).thenReturn(false);

        assertThrows(
                EntityNotFoundException.class,
                () -> service.deleteComment(1L));

        assertThrows(
                EntityNotFoundException.class,
                () -> service.deleteComment(0L));

        assertThrows(
                EntityNotFoundException.class,
                () -> service.deleteComment(null));

        verify(repository, never()).deleteById(anyLong());
    }

    private Comment createCommentWhitId(Long id) {
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
