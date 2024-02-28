package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.models.DTOs.CommentDTO;
import br.com.francaguilherme.myportfolio.models.entities.Comment;
import br.com.francaguilherme.myportfolio.models.entities.Project;
import br.com.francaguilherme.myportfolio.repositories.CommentRepository;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository repository;
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private CommentService service;

    private final Comment COMMENT = new Comment();
    private final Project PROJECT = new Project();

    @BeforeEach
    void setUp() {
        COMMENT.setId(1L);
        PROJECT.setId(1L);

        COMMENT.setProject(PROJECT);
    }

    @Test
    void testListComments() {
        List<Comment> comments = new ArrayList<>(List.of(COMMENT));

        when(repository.findAll()).thenReturn(comments);

        List<CommentDTO> commentDTOs = service.listComments();

        assertAll(
                () -> {
                    for (int i = 0; i < commentDTOs.size(); i++) {
                        assertNotNull(commentDTOs.get(i));
                        assertEquals(comments.get(i).getId(), commentDTOs.get(i).getId());
                        assertEquals(comments.get(i).getProject().getId(), commentDTOs.get(i).getProject_id());
                    }
                }
        );
    }

    @Test
    void testListCommentsEmptyList() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(EmptyListException.class, () -> service.listComments());
    }

    @Test
    void testListCommentsByProject() {
        List<Comment> comments = new ArrayList<>(List.of(COMMENT));

        when(repository.findByProjectId(1L)).thenReturn(comments);

        List<CommentDTO> commentDTOs = service.listCommentsByProject(1L);

        assertAll(
                () -> {
                    for (int i = 0; i < commentDTOs.size(); i++) {
                        assertNotNull(commentDTOs.get(i));
                        assertEquals(comments.get(i).getId(), commentDTOs.get(i).getId());
                        assertEquals(comments.get(i).getProject().getId(), commentDTOs.get(i).getProject_id());
                    }
                }
        );
    }

    @Test
    void testListCommentsByProjectEmptyList() {
        when(repository.findByProjectId(1L)).thenReturn(new ArrayList<>());

        assertThrows(EmptyListException.class, () -> service.listCommentsByProject(1L));
    }

    @Test
    void testSaveComment() {
        CommentDTO entryDTO = CommentDTO.toDTO(COMMENT);

        when(projectRepository.findById(entryDTO.getProject_id())).thenReturn(Optional.of(PROJECT));
        when(repository.save(any())).thenReturn(COMMENT);

        CommentDTO responseDTO = service.saveComment(entryDTO);

        assertAll(
                () -> assertEquals(entryDTO.getId(), responseDTO.getId()),
                () -> assertEquals(entryDTO.getProject_id(), responseDTO.getProject_id())
        );
    }

    @Test
    void testSavaCommentInvalidProject() {
        COMMENT.setProject(new Project());

        CommentDTO entryDTO = CommentDTO.toDTO(COMMENT);

        assertThrows(EntityNotFoundException.class, () -> service.saveComment(entryDTO));
    }

    @Test
    void testUpdateComment() {
        CommentDTO entryDTO = CommentDTO.toDTO(COMMENT);

        when(repository.existsById(COMMENT.getId())).thenReturn(true);
        when(projectRepository.findById(PROJECT.getId())).thenReturn(Optional.of(PROJECT));
        when(repository.save(any())).thenReturn(COMMENT);

        CommentDTO responseDTO = service.updateComment(entryDTO);

        assertEquals(entryDTO.getId(), responseDTO.getId());
        assertEquals(entryDTO.getProject_id(), responseDTO.getProject_id());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdateCommentInvalid() {
        CommentDTO entryDTO = CommentDTO.toDTO(COMMENT);

        when(repository.existsById(COMMENT.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.updateComment(entryDTO));
    }

    @Test
    void testVoteComment() {
        CommentDTO entryDTO = CommentDTO.toDTO(COMMENT);

        when(repository.findById(entryDTO.getId())).thenReturn(Optional.of(COMMENT));
        when(repository.save(COMMENT)).thenReturn(COMMENT);

        assertAll(
                () -> assertEquals(1, service.voteComment(entryDTO, "up").getUp()),
                () -> assertEquals(1, service.voteComment(entryDTO, "down").getDown()),
                () -> assertEquals(0, service.voteComment(entryDTO, "remove-up").getUp()),
                () -> assertEquals(0, service.voteComment(entryDTO, "remove-down").getDown())
        );
    }

    @Test
    void testDeleteComment() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteComment(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCommentInvalid() {
        when(repository.existsById(2L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.deleteComment(2L));

        verify(repository, never()).deleteById(2L);
    }
}
