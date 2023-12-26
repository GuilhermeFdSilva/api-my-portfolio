package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
