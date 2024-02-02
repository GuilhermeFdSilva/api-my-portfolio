package br.com.francaguilherme.myportfolio.models.DTOS;

import br.com.francaguilherme.myportfolio.models.DTOs.CommentDTO;
import br.com.francaguilherme.myportfolio.models.entities.Comment;
import br.com.francaguilherme.myportfolio.models.entities.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentDTOTest {
    private CommentDTO commentDTO;

    @BeforeEach
    void setUp() {
        commentDTO = new CommentDTO();
    }

    @Test
    void testSetAndGetId(){
        assertNull(commentDTO.getId());

        commentDTO.setId(1L);

        assertEquals(1L, commentDTO.getId());
    }

    @Test
    void testSetAndGetNick(){
        assertNull(commentDTO.getNick());

        commentDTO.setNick("nick");

        assertEquals("nick", commentDTO.getNick());
    }

    @Test
    void testSetAndGetMessage(){
        assertNull(commentDTO.getMessage());

        commentDTO.setMessage("message");

        assertEquals("message", commentDTO.getMessage());
    }

    @Test
    void testSetAndGetProject_id(){
        assertNull(commentDTO.getProject_id());

        commentDTO.setProject_id(1L);

        assertEquals(1L, commentDTO.getProject_id());
    }

    @Test
    void testSetAndGetUp(){
        assertEquals(0, commentDTO.getUp());

        commentDTO.setUp(10);

        assertEquals(10, commentDTO.getUp());
    }

    @Test
    void testSetAndGetDown(){
        assertEquals(0, commentDTO.getDown());

        commentDTO.setDown(10);

        assertEquals(10, commentDTO.getDown());
    }

    @Test
    void testToDTO() {
        Project project = new Project();
        project.setId(1L);

        Comment commentEntity = new Comment();
        commentEntity.setId(1L);
        commentEntity.setNick("nick");
        commentEntity.setMessage("message");
        commentEntity.setProject(project);
        commentEntity.setUp(10);
        commentEntity.setDown(10);

        commentDTO = CommentDTO.toDTO(commentEntity);

        assertAll(
                () -> assertEquals(commentEntity.getId(), commentDTO.getId()),
                () -> assertEquals(commentEntity.getNick(), commentDTO.getNick()),
                () -> assertEquals(commentEntity.getMessage(), commentDTO.getMessage()),
                () -> assertEquals(commentEntity.getProject().getId(), commentDTO.getProject_id()),
                () -> assertEquals(commentEntity.getUp(), commentDTO.getUp()),
                () -> assertEquals(commentEntity.getDown(), commentDTO.getDown())
        );
    }

    @Test
    void testListToDTO() {
        List<Comment> comments = new ArrayList<>();
        List<CommentDTO> commentDTOs;

        for (long i = 0; i < 3; i++) {
            Comment comment = new Comment();
            comment.setId(i);
            comment.setProject(new Project());

            comments.add(comment);
        }

        commentDTOs = CommentDTO.listToDTO(comments);

        assertAll(
                () -> {
                    for (int i = 0; i < commentDTOs.size(); i++) {
                        assertEquals(comments.get(i).getId(), commentDTOs.get(i).getId());
                    }
                }
        );
    }
}
