package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para entidade {@link Comment}. Este repositório fornece métodos para manipulação e persistência de dados
 * na tabela {@link Comment}.
 *
 * @see Repository
 * @see Comment
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
