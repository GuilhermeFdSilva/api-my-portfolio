package br.com.francaguilherme.myportfolio.models.entities;

import br.com.francaguilherme.myportfolio.models.DTOs.CommentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentTest {
    private Comment comment;
    private final Project PROJECT = new Project();

    @BeforeEach
    void setUp() {
        comment = new Comment();
    }

    @Test
    void testGetAndSetId() {
        assertNull(comment.getId());

        comment.setId(1L);

        assertEquals(1L, comment.getId());
    }

    @Test
    void testGetAndSetNick() {
        assertNull(comment.getNick());

        comment.setNick("nick");

        assertEquals("nick", comment.getNick());
    }

    @Test
    void testGetAndSetMessage() {
        assertNull(comment.getMessage());

        comment.setMessage("message");

        assertEquals("message", comment.getMessage());
    }

    @Test
    void testGetAndSetProject() {
        assertNull(comment.getProject());

        comment.setProject(PROJECT);

        assertEquals(PROJECT, comment.getProject());
    }

    @Test
    void testGetAndSetUp() {
        assertEquals(0, comment.getUp());

        comment.setUp(10);

        assertEquals(10, comment.getUp());
    }

    @Test
    void testGetAndSetDown() {
        assertEquals(0, comment.getDown());

        comment.setDown(10);

        assertEquals(10, comment.getDown());
    }

    @Test
    void testConstructFromDTO() {
        PROJECT.setId(1L);

        CommentDTO dto = new CommentDTO();
        dto.setId(1L);
        dto.setNick("nick");
        dto.setMessage("message");
        dto.setProject_id(1L);
        dto.setUp(10);
        dto.setDown(10);

        comment = new Comment(dto, PROJECT);

        assertAll(
                () -> assertEquals(dto.getId(), comment.getId()),
                () -> assertEquals(dto.getNick(), comment.getNick()),
                () -> assertEquals(dto.getMessage(), comment.getMessage()),
                () -> assertEquals(dto.getProject_id(), comment.getProject().getId()),
                () -> assertEquals(dto.getUp(), comment.getUp()),
                () -> assertEquals(dto.getDown(), comment.getDown())
        );
    }

    @Test
    void testIncrementAndDecrementVoteUp() {
        comment.decrementUpVote();

        assertEquals(0, comment.getUp());

        comment.incrementUpVote();

        assertEquals(1, comment.getUp());

        comment.decrementUpVote();

        assertEquals(0, comment.getUp());
    }

    @Test
    void testIncrementAndDecrementVoteDown() {
        comment.decrementDownVote();

        assertEquals(0, comment.getDown());

        comment.incrementDownVote();

        assertEquals(1, comment.getDown());

        comment.decrementDownVote();

        assertEquals(0, comment.getDown());
    }
}
