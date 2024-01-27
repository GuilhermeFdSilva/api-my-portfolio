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
        comment.setId(1L);
        comment.setNick("People");
        comment.setMessage("Nice message");
        comment.setProject(PROJECT);
        comment.setUp(0);
        comment.setDown(0);
    }

    @Test
    void testGetAndSetId() {
        assertEquals(1L, comment.getId());

        comment.setId(2L);

        assertEquals(2L, comment.getId());
    }

    @Test
    void testGetAndSetNick() {
        assertEquals("People", comment.getNick());

        comment.setNick("People2");

        assertEquals("People2", comment.getNick());
    }

    @Test
    void testGetAndSetMessage() {
        assertEquals("Nice message", comment.getMessage());

        comment.setMessage("Very nice message");

        assertEquals("Very nice message", comment.getMessage());
    }

    @Test
    void testGetAndSetProject() {
        assertEquals(PROJECT, comment.getProject());

        Project project2 = new Project();
        comment.setProject(project2);

        assertEquals(project2, comment.getProject());
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
        CommentDTO dto = new CommentDTO(comment);

        Comment commentTest = new Comment(dto, PROJECT);

        assertAll(
                () -> assertEquals(comment.getId(), commentTest.getId()),
                () -> assertEquals(comment.getNick(), commentTest.getNick()),
                () -> assertEquals(comment.getMessage(), commentTest.getMessage()),
                () -> assertEquals(comment.getProject(), commentTest.getProject()),
                () -> assertEquals(comment.getUp(), commentTest.getUp()),
                () -> assertEquals(comment.getDown(), commentTest.getDown())
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
