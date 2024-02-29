package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.models.entities.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminWrapperTest {
    private Admin admin;
    private Comment comment;
    private AdminWrapper<Comment> wrapper;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        comment = new Comment();

        wrapper = new AdminWrapper<>(admin, comment);
    }

    @Test
    void testSetAndGetAdmin() {
        assertEquals(admin, wrapper.getAdmin());

        admin = new Admin();
        wrapper.setAdmin(admin);

        assertEquals(admin, wrapper.getAdmin());
    }

    @Test
    void testSetAndGetType() {
        assertEquals(comment, wrapper.getType());

        comment = new Comment();
        wrapper.setType(comment);

        assertEquals(comment, wrapper.getType());
    }

    @Test
    void testNoArgsConstructor() {
        AdminWrapper<Comment> commentAdminWrapper = new AdminWrapper<>();

        assertNull(commentAdminWrapper.getAdmin());
        assertNull(commentAdminWrapper.getType());
    }
}
