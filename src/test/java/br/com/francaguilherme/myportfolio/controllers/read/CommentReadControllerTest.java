package br.com.francaguilherme.myportfolio.controllers.read;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.services.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentReadControllerTest {
    @Mock
    private CommentService service;
    @InjectMocks
    private CommentReadController controller;

    @Test
    void testListComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        when(service.listComments()).thenReturn(comments);

        ResponseEntity<?> response = controller.listComments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comments, response.getBody());

        verify(service, times(1)).listComments();
    }

    @Test
    void testListComments_NoContent() {
        when(service.listComments()).thenReturn(null);

        ResponseEntity<?> response = controller.listComments();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(service, times(1)).listComments();
    }

    @Test
    void testListCommentsByProject() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        when(service.listCommentsByProject(1L)).thenReturn(comments);

        ResponseEntity<?> response = controller.listCommentsByProject(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comments, response.getBody());

        verify(service, times(1)).listCommentsByProject(1L);
    }

    @Test
    void testListCommentsByProject_NoContent() {
        ResponseEntity<?> response = controller.listCommentsByProject(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(service, times(1)).listCommentsByProject(1L);
    }
}
