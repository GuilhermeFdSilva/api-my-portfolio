package br.com.francaguilherme.myportfolio.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CommentTest {
    private Comment comment;
    private Project project;

    @BeforeEach
    void setUp() {
        comment = new Comment();
        project = new Project();
        project.setId(1L);

        comment.setId(1L);
        comment.setNick("Guilherme");
        comment.setMessage("Very nice");
        comment.setUp(0);
        comment.setDown(0);
        comment.setProject(project);
    }

    @Test
    void testSetAndGetId() {
        assertEquals(1L, comment.getId());

        comment.setId(2L);

        assertEquals(2L, comment.getId());
    }

    @Test
    void testSetAndGetNick() {
        assertEquals("Guilherme", comment.getNick());

        comment.setNick("Evelin");

        assertEquals("Evelin", comment.getNick());
    }

    @Test
    void testSetAndGetMessage() {
        assertEquals("Very nice", comment.getMessage());

        comment.setMessage("Very very nice!!!");

        assertEquals("Very very nice!!!", comment.getMessage());
    }

    @Test
    void testSetAndGetUp() {
        assertEquals(0, comment.getUp());

        comment.setUp(10);

        assertEquals(10, comment.getUp());
    }

    @Test
    void testSetAndGetDown() {
        assertEquals(0, comment.getDown());

        comment.setDown(1);

        assertEquals(1, comment.getDown());
    }

    @Test
    void testSetAndGetProject() {
        assertEquals(project, comment.getProject());

        project = new Project();
        project.setId(2L);
        comment.setProject(project);

        assertEquals(project, comment.getProject());
    }

    @Test
    void testIncrementUpVote() {
        assertEquals(0, comment.getUp());

        comment.incrementUpVote();

        assertEquals(1, comment.getUp());
    }

    @Test
    void testDecrementUpVote() {
        comment.decrementUpVote();

        assertEquals(0, comment.getUp());

        comment.incrementUpVote();
        comment.decrementUpVote();

        assertEquals(0, comment.getUp());
    }

    @Test
    void testIncrementDownVote() {
        assertEquals(0, comment.getDown());

        comment.incrementDownVote();

        assertEquals(1, comment.getDown());
    }

    @Test
    void testDecrementDown() {
        comment.decrementDownVote();

        assertEquals(0, comment.getDown());

        comment.incrementDownVote();
        comment.decrementDownVote();

        assertEquals(0, comment.getDown());
    }
}
