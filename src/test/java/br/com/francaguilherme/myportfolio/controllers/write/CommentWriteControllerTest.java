package br.com.francaguilherme.myportfolio.controllers.write;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.services.AdminService;
import br.com.francaguilherme.myportfolio.services.CommentService;
import jakarta.persistence.EntityNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentWriteControllerTest {
    @Mock
    private AdminService adminService;
    @Mock
    private CommentService commentService;
    @Mock
    private Comment comment;
    @InjectMocks
    private CommentWriteController controller;

    @Test
    void testSaveComment() {
        when(commentService.saveComment(comment)).thenReturn(comment);

        ResponseEntity<?> response = controller.saveComment(comment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(comment, response.getBody());
        verify(commentService, times(1)).saveComment(comment);
    }

    @Test
    void testSaveComment_IllegalArgument() {
        when(commentService.saveComment(comment)).thenThrow(new IllegalArgumentException());

        ResponseEntity<?> response = controller.saveComment(comment);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Formato da requisição incorreto - " + new IllegalArgumentException().getMessage(), response.getBody());
        verify(commentService, times(1)).saveComment(comment);
    }

    @Test
    void testSaveComment_RuntimeException() {
        when(commentService.saveComment(comment)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = controller.saveComment(comment);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new Exception().getMessage(), response.getBody());
        verify(commentService, times(1)).saveComment(comment);
    }

    @Test
    void testVoteComment() {
        List<String> requests = new ArrayList<>(List.of("up", "down", "remove-up", "remove-down"));
        List<ResponseEntity<?>> responses = new ArrayList<>();

        when(commentService.updateComment(comment)).thenReturn(comment);

        requests.forEach((request) -> responses.add(controller.voteComment(request, comment)));

        assertAll(() -> responses.forEach((response) -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(comment, response.getBody());
        }));
        verify(comment, times(1)).incrementUpVote();
        verify(comment, times(1)).incrementDownVote();
        verify(comment, times(1)).decrementUpVote();
        verify(comment, times(1)).decrementDownVote();
    }

    @Test
    void testVoteComment_EntityNotFound() {
        when(commentService.updateComment(any())).thenThrow(new EntityNotFoundException());

        ResponseEntity<?> response = controller.voteComment("up", comment);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Objeto não encontrado - " + new EntityNotFoundException().getMessage(), response.getBody());
        verify(commentService, times(1)).updateComment(comment);
    }

    @Test
    void testVoteComment_IllegalArgument() {
        ResponseEntity<?> response = controller.voteComment("invalidArg", comment);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Formato da requisição incorreto - " + new IllegalArgumentException().getMessage(), response.getBody());
        verify(commentService, never()).updateComment(comment);
    }

    @Test
    void testVoteComment_RuntimeException() {
        when(commentService.updateComment(any())).thenThrow(new RuntimeException());

        ResponseEntity<?> response = controller.voteComment("up", comment);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new RuntimeException().getMessage(), response.getBody());
        verify(commentService, times(1)).updateComment(comment);
    }

    @Test
    void testDeleteComment_validPassword() {
        Admin admin = new Admin();

        when(adminService.validatePassword(any())).thenReturn(true);

        ResponseEntity<?> response = controller.deleteComment(1L, admin);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(commentService, times(1)).deleteComment(1L);
    }

    @Test
    void testDeleteComment_invalidPassword() {
        Admin admin = new Admin();

        when(adminService.validatePassword(any())).thenReturn(false);

        ResponseEntity<?> response = controller.deleteComment(1L, admin);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Autorização negada pelo servidor", response.getBody());
        verify(commentService, never()).deleteComment(anyLong());
    }

    @Test
    void testDeleteComment_AdminNotFound() {
        Admin admin = new Admin();

        when(adminService.validatePassword(any())).thenThrow(new AdminNotFoundException());

        ResponseEntity<?> response = controller.deleteComment(1L, admin);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Admin não encontrado - " + new AdminNotFoundException().getMessage(), response.getBody());
        verify(commentService, never()).deleteComment(anyLong());
    }

    @Test
    void testDeleteComment_RuntimeException() {
        Admin admin = new Admin();

        when(adminService.validatePassword(any())).thenThrow(new RuntimeException());

        ResponseEntity<?> response = controller.deleteComment(1L, admin);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new RuntimeException().getMessage(), response.getBody());
        verify(commentService, never()).deleteComment(anyLong());
    }
}
